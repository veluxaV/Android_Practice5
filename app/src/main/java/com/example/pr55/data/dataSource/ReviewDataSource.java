package com.example.pr55.data.dataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr55.data.repository.ReviewRepository;
import com.example.pr55.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDataSource {
    private static List<Review> reviews = new ArrayList<>();

    static {
        reviews.add(new Review(1, 1, 1, "Service was great!", 5, null));
        reviews.add(new Review(2, 2, 2, "Very poor service", 2, null));
        reviews.add(new Review(3, 3, 3, "Excellent experience", 5, null));
    }

    public LiveData<List<Review>> getAllReviews() {
        MutableLiveData<List<Review>> data = new MutableLiveData<>();
        data.setValue(reviews);
        return data;
    }

    public LiveData<Review> getReviewById(int id) {
        MutableLiveData<Review> data = new MutableLiveData<>();
        for (Review review : reviews) {
            if (review.getId() == id) {
                data.setValue(review);
                break;
            }
        }
        return data;
    }

    public void createReview(Review review, ReviewRepository.Callback<Review> callback) {
        review.setId(generateId());
        reviews.add(review);
        callback.onSuccess(review);
    }

    public void updateReview(Review review, ReviewRepository.Callback<Review> callback) {
        for (int i = 0; i < reviews.size(); i++) {
            Review currentReview = reviews.get(i);
            if (currentReview.getId() == review.getId()) {
                reviews.set(i, review);
                callback.onSuccess(review);
                break;
            }
        }
    }

    public void deleteReview(Review review, ReviewRepository.Callback<Review> callback) {
        reviews.removeIf(currentReview -> currentReview.getId() == review.getId());
        callback.onSuccess(review);
    }

    private int generateId() {
        int maxId = 0;
        for (Review review : reviews) {
            if (review.getId() > maxId) {
                maxId = review.getId();
            }
        }
        return maxId + 1;
    }
    public interface Callback<T> {
        void onSuccess(T data);

        void onError(Throwable throwable);
    }
}

