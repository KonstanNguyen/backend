package com.systems.backend.service.impl;

import com.systems.backend.repository.RatingRepository;
import com.systems.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
}
