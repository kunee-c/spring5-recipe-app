package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeRepository);
    }

    @Test
    public void shouldReturnIndexPage() {
        List<Recipe> recipes = new ArrayList<>();
        //when(recipeRepository.findAll()).thenReturn(recipes);

        //when(model.addAttribute(anyString(),any())).thenReturn(null);
        assertEquals("index",indexController.getIndexPage(model));

        verify(recipeRepository, times(1)).findAll();
        verify(model).addAttribute(eq("recipes"),anyList());
    }
}