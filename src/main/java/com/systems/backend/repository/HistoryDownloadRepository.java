package com.systems.backend.repository;

import com.systems.backend.model.HistoryDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDownloadRepository extends JpaRepository<HistoryDownload, HistoryDownload.HistoryDownloadId> {
}
