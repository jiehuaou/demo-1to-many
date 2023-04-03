package com.example.demo.update;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionDynamicUpdateStore extends JpaRepository<VersionDynamicUpdateObject, Long> {
}
