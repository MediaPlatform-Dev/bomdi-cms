package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractPeriod {

    @Column(name = "strt_ymd")
    private LocalDate startDate;
    @Column(name = "end_ymd")
    private LocalDate endDate;

    public ContractPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate) || startDate.equals(endDate)) {
            throw new IllegalArgumentException("종료일이 시작일 보다 빠를 수 없습니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
