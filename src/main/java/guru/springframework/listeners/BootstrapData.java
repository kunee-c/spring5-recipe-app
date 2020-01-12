package guru.springframework.listeners;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BootstrapData {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapData(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @EventListener
    public void initData(ContextRefreshedEvent contextStartedEvent) {

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        log.info("Event listener");
        Recipe guacamole = new Recipe();
        guacamole.setDescription("The BEST guacamole! EASY to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips.");
        guacamole.setCookTime(0);
        guacamole.setPrepTime(10);
        guacamole.setServings(4);
        guacamole.setDifficulty(Difficulty.EASY);

        Ingredient avocado = new Ingredient("avocado",new BigDecimal(2),eachUomOptional.get());
        guacamole.addIngredient(avocado);

        recipeRepository.save(guacamole);
    }

}
