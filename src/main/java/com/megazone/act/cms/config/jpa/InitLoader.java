package com.megazone.act.cms.config.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.repository.*;
import com.megazone.act.cms.domain.type.*;
import com.megazone.act.cms.domain.vo.*;

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

        List<ContractEmployee> employees = employeeRepository.saveAll(List.of(new Employee("김태현"), new Employee("임재진"), new Employee("구병선")))
            .stream().map(ContractEmployee::new).toList();

        Customer customer = new Customer("LG");
        customerRepository.save(customer);

        CustomerEmployee customerEmployee = new CustomerEmployee("홍길동", "megazon@mz.co.kr");
        customerEmployeeRepository.save(customerEmployee);

        ContractTypes types = new ContractTypes(ContractType.SALES, DealType.CONTRACT, SubmissionType.SALESFORCE, InvoiceType.TAX);
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(7));
        Period invoicePeriod = new Period(LocalDate.now(), LocalDate.now().plusDays(30));
        ContractMoney money = new ContractMoney(CurrencyUnitType.KRW, 3000, true);
        Contract contract = new Contract("테스트 계약", "테스트 계약 특이사항", "SF 계약번호",
            corporation, types, period, invoicePeriod, money,
            employees,
            customer,
            List.of(new ContractCustomerEmployee(customerEmployee)),
            List.of(new ContractDetail("계약 상세A", ContractDetailType.INFRA), new ContractDetail("계약 상세A", ContractDetailType.MANAGED_SERVICE))
        );

        contractRepository.save(contract);
    }
}
