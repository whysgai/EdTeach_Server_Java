package com.example.CS5610fa20serverjavacohenw.repositories;

import com.example.CS5610fa20serverjavacohenw.models.Widget;
import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
}
