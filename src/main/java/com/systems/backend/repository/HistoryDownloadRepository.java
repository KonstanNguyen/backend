package com.systems.backend.repository;

import com.systems.backend.model.HistoryDownload;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDownloadRepository extends JpaRepository<HistoryDownload, HistoryDownload.HistoryDownloadId> {
    List<HistoryDownload> findByAccountId(Long accountId);

    List<HistoryDownload> findByDocumentId(Long documentId);
}
