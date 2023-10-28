package com.megazone.act.cms.config.jpa;

import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.port.repository.*;
import com.megazone.act.cms.domain.type.*;
import com.megazone.act.cms.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.megazone.act.cms.domain.type.ContractDetailType.*;

@RequiredArgsConstructor
@Component
public class InitLoader implements ApplicationListener<ApplicationStartedEvent> {

    private final ContractRepository contractRepository;
    private final CorporationRepository corporationRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final CustomerEmployeeRepository customerEmployeeRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Corporation corporation = initCorporation();
        List<Employee> savedEmployees = initEmployees();

        Customer customerA = new Customer("LG");
        Customer customerB = new Customer("삼성전자");
        Customer customerC = new Customer("SKT");
        List<Customer> customers = customerRepository.saveAll(List.of(customerA, customerB, customerC));

        CustomerEmployee customerEmployeeA = new CustomerEmployee("홍길동", "megazon1@mz.co.kr");
        CustomerEmployee customerEmployeeB = new CustomerEmployee("김영철", "megazon2@mz.co.kr");
        customerEmployeeRepository.saveAll(List.of(customerEmployeeA, customerEmployeeB));

        List<ContractEmployee> contractEmployeesA = List.of(
            new ContractEmployee(savedEmployees.get(0), EmployeeRoleType.CONTRACT),
            new ContractEmployee(savedEmployees.get(1), EmployeeRoleType.SALES));
        List<ContractEmployee> contractEmployeesB = List.of(
            new ContractEmployee(savedEmployees.get(2), EmployeeRoleType.CONTRACT),
            new ContractEmployee(savedEmployees.get(3), EmployeeRoleType.SALES));
        List<ContractEmployee> contractEmployeesC = List.of(
            new ContractEmployee(savedEmployees.get(4), EmployeeRoleType.CONTRACT),
            new ContractEmployee(savedEmployees.get(5), EmployeeRoleType.SALES));
        List<ContractEmployee> contractEmployeesD = List.of(
            new ContractEmployee(savedEmployees.get(0), EmployeeRoleType.CONTRACT),
            new ContractEmployee(savedEmployees.get(3), EmployeeRoleType.SALES));
        List<ContractEmployee> contractEmployeesE = List.of(
            new ContractEmployee(savedEmployees.get(2), EmployeeRoleType.CONTRACT),
            new ContractEmployee(savedEmployees.get(4), EmployeeRoleType.SALES));
        List<ContractEmployee> contractEmployeesF = List.of(new ContractEmployee(savedEmployees.get(6), EmployeeRoleType.CONTRACT));

        List<Contract> contracts = List.of(
            contractFixture(corporation, contractEmployeesA, customerA, customerEmployeeA, "A"),
            contractFixture(corporation, contractEmployeesB, customerB, customerEmployeeA, "B"),
            contractFixture(corporation, contractEmployeesC, customerC, customerEmployeeB, "C"),
            contractFixture(corporation, contractEmployeesD, customerA, customerEmployeeB, "D"),
            contractFixture(corporation, contractEmployeesE, customerC, customerEmployeeB, "E"),
            contractFixtureOtherDetails(corporation, contractEmployeesF, customerB, customerEmployeeA, "F")
        );
        contractRepository.saveAll(contracts);
    }

    private Corporation initCorporation() {
        Corporation corporation = new Corporation("메가존 클라우드");
        corporationRepository.save(corporation);
        return corporation;
    }

    private List<Employee> initEmployees() {
        List<Employee> employees = List.of(
            new Employee("김태현"),
            new Employee("임재진"),
            new Employee("임성진"),
            new Employee("구병선"),
            new Employee("박수진"),
            new Employee("한종민"),
            new Employee("최지훈")
        );
        return employeeRepository.saveAll(employees);
    }

    private Contract contractFixture(Corporation corporation, List<ContractEmployee> contractEmployees, Customer customer, CustomerEmployee customerEmployee, String suffix) {
        ContractTypes types = new ContractTypes(ContractType.SALES, DealType.CONTRACT, SubmissionType.SALESFORCE, InvoiceType.TAX);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(7));
        Period invoicePeriod = new Period(LocalDate.now(), LocalDate.now().plusDays(30));
        ContractMoney money = new ContractMoney(CurrencyUnitType.KRW, 3000, "금액 특이사항" + suffix);

        return new Contract("테스트 계약" + suffix, "테스트 계약 특이사항" + suffix, "SF 계약번호" + suffix,
            corporation, types, period, invoicePeriod, money,
            contractEmployees,
            "testLink" + suffix,
            customer,
            List.of(new ContractCustomerEmployee(customerEmployee)),
            List.of(contractDetailFixture(suffix, INFRA), contractDetailFixture(suffix, MANAGED_SERVICE))
        );
    }

    private Contract contractFixtureOtherDetails(
        Corporation corporation,
        List<ContractEmployee> contractEmployees,
        Customer customer,
        CustomerEmployee customerEmployee,
        String suffix
    ) {
        ContractTypes types = new ContractTypes(ContractType.SALES, DealType.CONTRACT, SubmissionType.SALESFORCE, InvoiceType.TAX);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(7));
        Period invoicePeriod = new Period(LocalDate.now(), LocalDate.now().plusDays(30));
        ContractMoney money = new ContractMoney(CurrencyUnitType.KRW, 3000, "금액 특이사항" + suffix);

        return new Contract("테스트 계약" + suffix, "테스트 계약 특이사항" + suffix, "SF 계약번호" + suffix,
            corporation, types, period, invoicePeriod, money,
            contractEmployees,
            "testLink" + suffix,
            customer,
            List.of(new ContractCustomerEmployee(customerEmployee)),
            List.of(contractDetailFixture(suffix, DIGITAL_PRODUCT), contractDetailFixture(suffix, PROFESSIONAL_SERVICE))
        );
    }

    private static ContractDetail contractDetailFixture(String suffix, ContractDetailType type) {
        return new ContractDetail(String.format("계약 상세%s %s", suffix, type.getDescription()), type);
    }
}
