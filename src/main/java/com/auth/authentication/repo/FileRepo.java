package com.auth.authentication.repo;

import com.auth.authentication.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<File, Short> {
}
