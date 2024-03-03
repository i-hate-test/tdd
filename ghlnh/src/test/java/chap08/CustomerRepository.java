package chap08;

public interface CustomerRepository {
    Customer findOne(String id);
}
