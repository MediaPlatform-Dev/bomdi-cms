insert into tb_crprtn_m(crprtn_nm)
values ('메가존');

insert into customer(name)
values ('LG');

insert into customer_employee(name)
values ('홍길동C');

insert into tb_cntrct_m(cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        currency_unit_type, amt, tot_amt)
values ('매출 테스트 계약', '매출 테스트 설명',
        '2023-01-01', '2023-12-01',
        'SALES', 'CONTRACT', 'SALESFORCE',
        'KRW', 3000, 3000);

insert into tb_cntrct_m(cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        currency_unit_type, amt, tot_amt)
values ('매입 테스트 계약 2', '매입 테스트 설명 2',
        '2023-01-01', '2023-12-01',
        'PURCHASE', 'CONTRACT', 'SALESFORCE',
        'KRW', 1000, 1000);

insert into department(name)
values ('ITO');

insert into employee(name, department_id)
values ('홍길동A', 1),
       ('홍길동B', 1);
