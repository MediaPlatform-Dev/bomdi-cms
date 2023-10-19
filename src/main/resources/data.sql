insert into tb_crprtn_m(crprtn_id, crprtn_nm)
values (1L, '메가존');

insert into customer_employee(cstmr_id, cstmr_nm)
values (1L, 'LG');

insert into tb_cntrct_m(cntrct_id, cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        crprtn_id, cstmr_id,
                        currency_unit_type, amt, tot_amt)
values (1L, '매출 테스트 계약', '매출 테스트 설명',
        '2023-01-01', '2023-12-01',
        'SALES', 'CONTRACT', 'SALESFORCE',
        1L, 1L,
        'KRW', 3000, 3000);

insert into tb_cntrct_m(cntrct_id, cntrct_nm, rmrk,
                        strt_ymd, end_ymd,
                        cntrct_gb1_cd, cntrct_clas_cd, src_system_cd,
                        crprtn_id, cstmr_id,
                        currency_unit_type, amt, tot_amt)
values (2L, '매입 테스트 계약 2', '매입 테스트 설명 2',
        '2023-01-01', '2023-12-01',
        'PURCHASE', 'CONTRACT', 'SALESFORCE',
        1L, 1L,
        'KRW', 1000, 1000);
