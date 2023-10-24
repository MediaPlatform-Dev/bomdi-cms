-- 자사
insert into tb_crprtn_m(crprtn_nm)
values ('메가존 클라우드');

insert into department(name)
values ('ITO');

insert into employee(name)
values ('김태현'),
       ('임재진'),
       ('구병선');

-- 고객사
insert into customer(name)
values ('LG'),
       ('삼성전자');

insert into customer_employee(name)
values ('이유종'),
       ('손영재');

-- 계약
insert into tb_cntrct_m(cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        currency_unit_type, amt, tot_amt, has_vat)
values ('매출 테스트 계약', '매출 테스트 설명',
        '2023-01-01', '2023-12-01',
        'SALES', 'CONTRACT', 'SALESFORCE',
        'KRW', 3000, 3000, true),
       ('매출 테스트 계약 2', '매출 테스트 설명 2',
        '2023-01-01', '2023-12-01',
        'SALES', 'CONTRACT', 'SALESFORCE',
        'KRW', 1000, 1000, true);
