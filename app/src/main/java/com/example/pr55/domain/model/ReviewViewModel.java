package com.example.pr55.domain.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr55.data.dataSource.ReviewDataSource;
import com.example.pr55.data.repository.ReviewRepository;
import com.example.pr55.domain.model.Review;

import java.util.List;

public class ReviewViewModel extends ViewModel {
    private ReviewRepository reviewRepository;

    public ReviewViewModel() {
        reviewRepository = new ReviewRepository(new ReviewDataSource());
    }

    public LiveData<List<Review>> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public LiveData<Review> getReviewById(int id) {
        return reviewRepository.getReviewById(id);
    }

    public void addReview(Review review) {
        reviewRepository.addReview(review, new ReviewRepository.Callback<Review>() {
            @Override
            public void onSuccess(Review data) {
                // Обработка успешного добавления отзыва
            }

            @Override
            public void onError(Throwable throwable) {
                // Обработка ошибки добавления отзыва
            }
        });
    }

    public void updateReview(Review review) {
        reviewRepository.updateReview(review, new ReviewRepository.Callback<Review>() {
            @Override
            public void onSuccess(Review data) {
                // Обработка успешного обновления отзыва
            }

            @Override
            public void onError(Throwable throwable) {
                // Обработка ошибки обновления отзыва
            }
        });
    }

    public void deleteReview(Review review) {
        reviewRepository.deleteReview(review, new ReviewRepository.Callback<Review>() {
            @Override
            public void onSuccess(Review data) {
                // Обработка успешного удаления отзыва
            }

            @Override
            public void onError(Throwable throwable) {
                // Обработка ошибки удаления отзыва
            }
        });
    }
}
