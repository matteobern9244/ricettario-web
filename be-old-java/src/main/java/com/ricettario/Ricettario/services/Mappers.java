package com.ricettario.Ricettario.services;

import com.ricettario.Ricettario.model.dto.*;
import com.ricettario.Ricettario.model.entities.*;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mappers {
    @Autowired
    ModelMapper modelMapper;

    //Receipe
    public ReceipeDTO convertToReceipeDTO(Receipe receipe) {
        return modelMapper.map(receipe, ReceipeDTO.class);
    }
    public Receipe convertToReceipeEntity(ReceipeDTO receipeDTO) {
        return modelMapper.map(receipeDTO, Receipe.class);
    }

    //Category
    public CategoryDTO convertToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
    public Category convertToCategoryEntity(CategoryDTO categoryDTO) { return modelMapper.map(categoryDTO, Category.class); }

    //Cost
    public CostDTO convertToCostDTO(Cost cost) {
        return modelMapper.map(cost, CostDTO.class);
    }
    public Cost convertToCostEntity(CostDTO costDTO) { return modelMapper.map(costDTO, Cost.class); }

    //Difficult
    public DifficultDTO convertToDifficultDTO(Difficult difficult) { return modelMapper.map(difficult, DifficultDTO.class); }
    public Difficult convertToDifficultEntity(DifficultDTO difficultDTO) { return modelMapper.map(difficultDTO, Difficult.class); }

    //Hashtag
    public HashtagDTO convertToHashtagDTO(Hashtag hashtag) { return modelMapper.map(hashtag, HashtagDTO.class); }
    public Hashtag convertToHashtagEntity(HashtagDTO hashtagDTO) { return modelMapper.map(hashtagDTO, Hashtag.class); }

    //Ingredient
    public IngredientDTO convertToIngredientDTO(Ingredient ingredient) { return modelMapper.map(ingredient, IngredientDTO.class); }
    public Ingredient convertToIngredientEntity(IngredientDTO ingredientDTO) { return modelMapper.map(ingredientDTO, Ingredient.class); }

    //ReceipeIngredient
    public ReceipeIngredientDTO convertToReceipeIngredientDTO(ReceipeIngredient receipeIngredient) { return modelMapper.map(receipeIngredient,ReceipeIngredientDTO.class); }
    public ReceipeIngredient convertToReceipeIngredientEntity(ReceipeIngredientDTO receipeIngredientDTO) { return modelMapper.map(receipeIngredientDTO,ReceipeIngredient.class); }

    //ReceipeFavoritesUser
    public ReceipeFavoritesUserDTO convertToReceipeFavoritesUserDTO(ReceipeFavoritesUser receipeFavoritesUser) { return modelMapper.map(receipeFavoritesUser, ReceipeFavoritesUserDTO.class); }
    public ReceipeFavoritesUser convertToReceipeFavoritesUserEntity(ReceipeFavoritesUserDTO receipeFavoritesUserDTO) { return modelMapper.map(receipeFavoritesUserDTO, ReceipeFavoritesUser.class); }
}