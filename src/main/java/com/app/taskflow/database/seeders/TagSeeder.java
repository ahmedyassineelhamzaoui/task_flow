package com.app.taskflow.database.seeders;

import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagSeeder {

    private final TagRepository tagRepository;

    public void seed() {
        if (tagRepository.count() == 0) {
            List<Tag> tags = List.of(
                    Tag.builder().name("Urgent").build(),
                    Tag.builder().name("High Priority").build(),
                    Tag.builder().name("Medium Priority").build(),
                    Tag.builder().name("Low Priority").build(),
                    Tag.builder().name("Bug").build(),
                    Tag.builder().name("Feature").build(),
                    Tag.builder().name("Improvement").build(),
                    Tag.builder().name("Research").build(),
                    Tag.builder().name("Documentation").build(),
                    Tag.builder().name("Design").build(),
                    Tag.builder().name("Testing").build(),
                    Tag.builder().name("Review").build(),
                    Tag.builder().name("Refactor").build(),
                    Tag.builder().name("Cleanup").build(),
                    Tag.builder().name("Deployment").build(),
                    Tag.builder().name("Maintenance").build(),
                    Tag.builder().name("Performance").build(),
                    Tag.builder().name("Security").build(),
                    Tag.builder().name("Frontend").build(),
                    Tag.builder().name("Backend").build(),
                    Tag.builder().name("API").build(),
                    Tag.builder().name("Database").build(),
                    Tag.builder().name("Integration").build(),
                    Tag.builder().name("UI/UX").build(),
                    Tag.builder().name("Mobile").build(),
                    Tag.builder().name("Desktop").build(),
                    Tag.builder().name("Web").build(),
                    Tag.builder().name("DevOps").build(),
                    Tag.builder().name("QA").build(),
                    Tag.builder().name("Release").build(),
                    Tag.builder().name("Customer Issue").build(),
                    Tag.builder().name("Support").build()
            );
            tagRepository.saveAll(tags);
        }
    }
}
