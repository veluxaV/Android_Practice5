package com.example.pr55.data.repository;

import androidx.lifecycle.LiveData;

import com.example.pr55.data.dataSource.ReviewDataSource;
import com.example.pr55.data.model.Review;

import java.util.List;

public class ReviewRepository {
    private final ReviewDataSource reviewDataSource;

    public ReviewRepository(ReviewDataSource reviewDataSource) {
        this.reviewDataSource = reviewDataSource;
    }

    public LiveData<List<Review>> getAllReviews() {
        return reviewDataSource.getAllReviews();
    }

    public LiveData<Review> getReviewById(int id) {
        return reviewDataSource.getReviewById(id);
    }

    public void addReview(Review review, Callback<Review> callback) {
        reviewDataSource.createReview(review, callback);
    }

    public void updateReview(Review review, Callback<Review> callback) {
        reviewDataSource.updateReview(review, callback);
    }

    public void deleteReview(Review review, Callback<Review> callback) {
        reviewDataSource.deleteReview(review, callback);
    }

    public interface Callback<T> extends ReviewDataSource.Callback<T> {
        void onSuccess(T data);

        void onError(Throwable throwable);
    }
}

