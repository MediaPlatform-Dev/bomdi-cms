package com.megazone.act.cms.config.jpa;

import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.repository.*;
import com.megazone.act.cms.domain.type.*;
import com.megazone.act.cms.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static com.megazone.act.cms.domain.type.ContractDetailType.INFRA;
import static com.megazone.act.cms.domain.type.ContractDetailType.MANAGED_SERVICE;

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
        Corporation corporation = new Corporation("메가존 클라우드");
        corporationRepository.save(corporation);

        List<Employee> employees = List.of(new Employee("김태현"), new Employee("임재진"), new Employee("구병선"));
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);


        Customer customer = new Customer("LG");
        customerRepository.save(customer);
        CustomerEmployee customerEmployee = new CustomerEmployee("홍길동", "megazon@mz.co.kr");
        customerEmployeeRepository.save(customerEmployee);

        List<ContractEmployee> contractEmployeesA = Stream.of(savedEmployees.get(0), savedEmployees.get(1))
            .map(ContractEmployee::new)
            .toList();
        List<ContractEmployee> contractEmployeesB = Stream.of(savedEmployees.get(0), savedEmployees.get(1))
            .map(ContractEmployee::new)
            .toList();
        List<ContractEmployee> contractEmployeesC = Stream.of(savedEmployees.get(0), savedEmployees.get(1))
            .map(ContractEmployee::new)
            .toList();
        List<Contract> contracts = List.of(contractFixture(corporation, contractEmployeesA, customer, customerEmployee, "A"),
            contractFixture(corporation, contractEmployeesB, customer, customerEmployee, "B"),
            contractFixture(corporation, contractEmployeesC, customer, customerEmployee, "C")
        );
        contractRepository.saveAll(contracts);
    }

    private Contract contractFixture(Corporation corporation, List<ContractEmployee> contractEmployees, Customer customer, CustomerEmployee customerEmployee, String suffix) {
        ContractTypes types = new ContractTypes(ContractType.SALES, DealType.CONTRACT, SubmissionType.SALESFORCE, InvoiceType.TAX);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(7));
        Period invoicePeriod = new Period(LocalDate.now(), LocalDate.now().plusDays(30));
        ContractMoney money = new ContractMoney(CurrencyUnitType.KRW, 3000, true, "금액 특이사항" + suffix);

        return new Contract("테스트 계약" + suffix, "테스트 계약 특이사항" + suffix, "SF 계약번호" + suffix,
            corporation, types, period, invoicePeriod, money,
            contractEmployees,
            "testLink" + suffix,
            customer,
            List.of(new ContractCustomerEmployee(customerEmployee)),
            List.of(contractDetailFixture(suffix, INFRA), contractDetailFixture(suffix, MANAGED_SERVICE))
        );
    }

    private static ContractDetail contractDetailFixture(String suffix, ContractDetailType type) {
        return new ContractDetail(String.format("계약 상세%s %s", suffix, type.getDescription()), type);
    }
}
