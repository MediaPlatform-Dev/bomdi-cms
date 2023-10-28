package com.megazone.act.cms.domain.vo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Period {

    @Column(name = "strt_ymd")
    private LocalDate startDate;
    @Column(name = "end_ymd")
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);

        if (!isNotNull(startDate, endDate)) {
            this.startDate = LocalDate.now();
            this.endDate = LocalDate.now();
            return;
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static void validate(LocalDate startDate, LocalDate endDate) {
        if (isNotNull(startDate, endDate)) {
            if (endDate.isBefore(startDate) || startDate.equals(endDate)) {
                throw new IllegalArgumentException("종료일이 시작일 보다 빠를 수 없습니다.");
            }
        }
    }

    private static boolean isNotNull(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null;
    }
}
