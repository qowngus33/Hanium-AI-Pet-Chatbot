package com.example.myapplication.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 데이터 또는 오류 예외가 있는 결과 성공을 보유하는 일반 클래스입니다. */

public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    //하위 클래스 유형을 제한하기 위해 개인 생성자를 숨깁니다(성공, 오류).
    private Result() {
    }

    @Override
    public String toString() {
        //Result 성공하면 성공했을 때 설정한 데이터 값 보여줌.
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";

            //Result 실패하면 실패했을 때 설정한 데이터 값 보여줌.
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success 하위 클래스
    public final static class Success<T> extends Result {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error 하위 클래스
    public final static class Error extends Result {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}