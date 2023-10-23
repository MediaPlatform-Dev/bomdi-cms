insert into tb_crprtn_m(crprtn_id, crprtn_nm)
values (1L, '메가존');

insert into customer_employee(id, name)
values (1L, 'LG');

insert into tb_cntrct_m(cntrct_id, cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        currency_unit_type, amt, tot_amt)
values (1L, '매출 테스트 계약', '매출 테스트 설명',
        '2023-01-01', '2023-12-01',
        'SALES', 'CONTRACT', 'SALESFORCE',
        'KRW', 3000, 3000);

insert into tb_cntrct_m(cntrct_id, cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        currency_unit_type, amt, tot_amt)
values (2L, '매입 테스트 계약 2', '매입 테스트 설명 2',
        '2023-01-01', '2023-12-01',
        'PURCHASE', 'CONTRACT', 'SALESFORCE',
        'KRW', 1000, 1000);

insert into department(department_id, name)
values (1L, 'ITO');

insert into employee(id, name, department_id)
values (10, '홍길동', 1L);
