package app.ladderproject.kafka.service;

public interface Handler {

    void onSuccess();

    void onFailure(Throwable throwable);
}
