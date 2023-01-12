package dtos

import enums.RatingService

case class ReviewRequestDto(requestId: Long, review: String, rating: RatingService.Rating)
