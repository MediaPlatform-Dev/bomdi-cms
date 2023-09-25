package com.megazone.bomdi.cms.repository;

import com.megazone.bomdi.cms.domain.Contract;
import com.megazone.bomdi.cms.domain.Contractor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.RevisionMetadata.RevisionType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계약 이력 확인 테스트")
@SpringBootTest
class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @DisplayName("데이터를 생성하면 이력이 남는다.")
    @Test
    void createHistoryTest() {
        Contract contract = saveFixture();

        var revision = contractRepository.findLastChangeRevision(contract.getId()).orElseThrow();

        var revisionType = revision.getMetadata().getRevisionType();
        assertThat(revisionType).isEqualTo(RevisionType.INSERT);
    }

    @DisplayName("데이터 수정 후 이전 이력들을 확인할 수 있다.")
    @Test
    void updateHistoryTest() {
        Contract contract = saveFixture();

        contract.update("제목 수정 1", "내용 수정 1");
        contractRepository.save(contract);

        var revisions = contractRepository.findRevisions(contract.getId()).getContent();
        assertThat(revisions).hasSize(2);
        assertThat(revisions.get(0).getEntity().getName()).isEqualTo("테스트");
        assertThat(revisions.get(0).getMetadata().getRevisionType()).isEqualTo(RevisionType.INSERT);
        assertThat(revisions.get(1).getEntity().getName()).isEqualTo("제목 수정 1");
        assertThat(revisions.get(1).getMetadata().getRevisionType()).isEqualTo(RevisionType.UPDATE);
    }

    private Contract saveFixture() {
        Contract contract = new Contract("테스트", "테스트", new Contractor("성준혁"));
        contractRepository.save(contract);
        return contract;
    }
}
