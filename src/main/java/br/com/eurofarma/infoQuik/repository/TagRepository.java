package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
