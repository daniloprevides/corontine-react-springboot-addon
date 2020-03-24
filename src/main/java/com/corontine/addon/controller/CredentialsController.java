package com.corontine.addon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.corontine.addon.dto.AddonResponseDTO;
import com.corontine.addon.dto.AuthorizationCodeDTO;
import com.corontine.addon.dto.ClientCredentialsDTO;
import com.corontine.addon.dto.ScopesDTO;
import com.corontine.addon.model.RecipeEntity;
import com.corontine.addon.services.ClientCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CredentialsController {

    @Autowired()
    public ClientCredentialsService clientCredentialsService;

    @GetMapping("/info")
    public AuthorizationCodeDTO info(@RequestParam(value = "callback_uri") String callbackUri) {
        return clientCredentialsService.getCode();
    }

    @PutMapping("/callback")
    public AddonResponseDTO info(@RequestBody() ClientCredentialsDTO clientCredentialsDTO) {
        return clientCredentialsService.addPlugin(clientCredentialsDTO);
    }

}