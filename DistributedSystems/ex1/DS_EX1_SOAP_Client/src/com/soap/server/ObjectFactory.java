
package com.soap.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soap.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DatabaseException_QNAME = new QName("http://server.soap.com/", "DatabaseException");
    private final static QName _AddNewCustomerReview_QNAME = new QName("http://server.soap.com/", "addNewCustomerReview");
    private final static QName _AddNewCustomerReviewResponse_QNAME = new QName("http://server.soap.com/", "addNewCustomerReviewResponse");
    private final static QName _AddNewProduct_QNAME = new QName("http://server.soap.com/", "addNewProduct");
    private final static QName _AddNewProductResponse_QNAME = new QName("http://server.soap.com/", "addNewProductResponse");
    private final static QName _AddNewStore_QNAME = new QName("http://server.soap.com/", "addNewStore");
    private final static QName _AddNewStoreResponse_QNAME = new QName("http://server.soap.com/", "addNewStoreResponse");
    private final static QName _AddNewUser_QNAME = new QName("http://server.soap.com/", "addNewUser");
    private final static QName _AddNewUserResponse_QNAME = new QName("http://server.soap.com/", "addNewUserResponse");
    private final static QName _AddToCart_QNAME = new QName("http://server.soap.com/", "addToCart");
    private final static QName _AddToCartResponse_QNAME = new QName("http://server.soap.com/", "addToCartResponse");
    private final static QName _DeleteCustomerReview_QNAME = new QName("http://server.soap.com/", "deleteCustomerReview");
    private final static QName _DeleteCustomerReviewResponse_QNAME = new QName("http://server.soap.com/", "deleteCustomerReviewResponse");
    private final static QName _DeleteFromCart_QNAME = new QName("http://server.soap.com/", "deleteFromCart");
    private final static QName _DeleteFromCartResponse_QNAME = new QName("http://server.soap.com/", "deleteFromCartResponse");
    private final static QName _DeleteProduct_QNAME = new QName("http://server.soap.com/", "deleteProduct");
    private final static QName _DeleteProductResponse_QNAME = new QName("http://server.soap.com/", "deleteProductResponse");
    private final static QName _DeleteStore_QNAME = new QName("http://server.soap.com/", "deleteStore");
    private final static QName _DeleteStoreResponse_QNAME = new QName("http://server.soap.com/", "deleteStoreResponse");
    private final static QName _DeleteUser_QNAME = new QName("http://server.soap.com/", "deleteUser");
    private final static QName _DeleteUserResponse_QNAME = new QName("http://server.soap.com/", "deleteUserResponse");
    private final static QName _GetAllCustomerReviews_QNAME = new QName("http://server.soap.com/", "getAllCustomerReviews");
    private final static QName _GetAllCustomerReviewsResponse_QNAME = new QName("http://server.soap.com/", "getAllCustomerReviewsResponse");
    private final static QName _GetAllProducts_QNAME = new QName("http://server.soap.com/", "getAllProducts");
    private final static QName _GetAllProductsAndPricesInStore_QNAME = new QName("http://server.soap.com/", "getAllProductsAndPricesInStore");
    private final static QName _GetAllProductsAndPricesInStoreResponse_QNAME = new QName("http://server.soap.com/", "getAllProductsAndPricesInStoreResponse");
    private final static QName _GetAllProductsByCategory_QNAME = new QName("http://server.soap.com/", "getAllProductsByCategory");
    private final static QName _GetAllProductsByCategoryResponse_QNAME = new QName("http://server.soap.com/", "getAllProductsByCategoryResponse");
    private final static QName _GetAllProductsByMaxPrice_QNAME = new QName("http://server.soap.com/", "getAllProductsByMaxPrice");
    private final static QName _GetAllProductsByMaxPriceResponse_QNAME = new QName("http://server.soap.com/", "getAllProductsByMaxPriceResponse");
    private final static QName _GetAllProductsResponse_QNAME = new QName("http://server.soap.com/", "getAllProductsResponse");
    private final static QName _GetAllStores_QNAME = new QName("http://server.soap.com/", "getAllStores");
    private final static QName _GetAllStoresAndPricesBySpecificProduct_QNAME = new QName("http://server.soap.com/", "getAllStoresAndPricesBySpecificProduct");
    private final static QName _GetAllStoresAndPricesBySpecificProductResponse_QNAME = new QName("http://server.soap.com/", "getAllStoresAndPricesBySpecificProductResponse");
    private final static QName _GetAllStoresResponse_QNAME = new QName("http://server.soap.com/", "getAllStoresResponse");
    private final static QName _GetAllUserIDsThatBoughtTheProduct_QNAME = new QName("http://server.soap.com/", "getAllUserIDsThatBoughtTheProduct");
    private final static QName _GetAllUserIDsThatBoughtTheProductResponse_QNAME = new QName("http://server.soap.com/", "getAllUserIDsThatBoughtTheProductResponse");
    private final static QName _GetAllUsers_QNAME = new QName("http://server.soap.com/", "getAllUsers");
    private final static QName _GetAllUsersResponse_QNAME = new QName("http://server.soap.com/", "getAllUsersResponse");
    private final static QName _GetAverageRatingOfProduct_QNAME = new QName("http://server.soap.com/", "getAverageRatingOfProduct");
    private final static QName _GetAverageRatingOfProductResponse_QNAME = new QName("http://server.soap.com/", "getAverageRatingOfProductResponse");
    private final static QName _GetCustomerReviewByID_QNAME = new QName("http://server.soap.com/", "getCustomerReviewByID");
    private final static QName _GetCustomerReviewByIDResponse_QNAME = new QName("http://server.soap.com/", "getCustomerReviewByIDResponse");
    private final static QName _GetProductByID_QNAME = new QName("http://server.soap.com/", "getProductByID");
    private final static QName _GetProductByIDResponse_QNAME = new QName("http://server.soap.com/", "getProductByIDResponse");
    private final static QName _GetProductIDsAndStoreIDsBought_QNAME = new QName("http://server.soap.com/", "getProductIDsAndStoreIDsBought");
    private final static QName _GetProductIDsAndStoreIDsBoughtResponse_QNAME = new QName("http://server.soap.com/", "getProductIDsAndStoreIDsBoughtResponse");
    private final static QName _GetProductIDsAndStoreIDsFromCart_QNAME = new QName("http://server.soap.com/", "getProductIDsAndStoreIDsFromCart");
    private final static QName _GetProductIDsAndStoreIDsFromCartResponse_QNAME = new QName("http://server.soap.com/", "getProductIDsAndStoreIDsFromCartResponse");
    private final static QName _GetStoreByID_QNAME = new QName("http://server.soap.com/", "getStoreByID");
    private final static QName _GetStoreByIDResponse_QNAME = new QName("http://server.soap.com/", "getStoreByIDResponse");
    private final static QName _GetUserByID_QNAME = new QName("http://server.soap.com/", "getUserByID");
    private final static QName _GetUserByIDResponse_QNAME = new QName("http://server.soap.com/", "getUserByIDResponse");
    private final static QName _LinkStoreAndProduct_QNAME = new QName("http://server.soap.com/", "linkStoreAndProduct");
    private final static QName _LinkStoreAndProductResponse_QNAME = new QName("http://server.soap.com/", "linkStoreAndProductResponse");
    private final static QName _PayForUserCart_QNAME = new QName("http://server.soap.com/", "payForUserCart");
    private final static QName _PayForUserCartResponse_QNAME = new QName("http://server.soap.com/", "payForUserCartResponse");
    private final static QName _UpdateCustomerReview_QNAME = new QName("http://server.soap.com/", "updateCustomerReview");
    private final static QName _UpdateCustomerReviewResponse_QNAME = new QName("http://server.soap.com/", "updateCustomerReviewResponse");
    private final static QName _UpdateProduct_QNAME = new QName("http://server.soap.com/", "updateProduct");
    private final static QName _UpdateProductResponse_QNAME = new QName("http://server.soap.com/", "updateProductResponse");
    private final static QName _UpdateStore_QNAME = new QName("http://server.soap.com/", "updateStore");
    private final static QName _UpdateStoreResponse_QNAME = new QName("http://server.soap.com/", "updateStoreResponse");
    private final static QName _UpdateUser_QNAME = new QName("http://server.soap.com/", "updateUser");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://server.soap.com/", "updateUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soap.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatabaseException }
     * 
     */
    public DatabaseException createDatabaseException() {
        return new DatabaseException();
    }

    /**
     * Create an instance of {@link AddNewCustomerReview }
     * 
     */
    public AddNewCustomerReview createAddNewCustomerReview() {
        return new AddNewCustomerReview();
    }

    /**
     * Create an instance of {@link AddNewCustomerReviewResponse }
     * 
     */
    public AddNewCustomerReviewResponse createAddNewCustomerReviewResponse() {
        return new AddNewCustomerReviewResponse();
    }

    /**
     * Create an instance of {@link AddNewProduct }
     * 
     */
    public AddNewProduct createAddNewProduct() {
        return new AddNewProduct();
    }

    /**
     * Create an instance of {@link AddNewProductResponse }
     * 
     */
    public AddNewProductResponse createAddNewProductResponse() {
        return new AddNewProductResponse();
    }

    /**
     * Create an instance of {@link AddNewStore }
     * 
     */
    public AddNewStore createAddNewStore() {
        return new AddNewStore();
    }

    /**
     * Create an instance of {@link AddNewStoreResponse }
     * 
     */
    public AddNewStoreResponse createAddNewStoreResponse() {
        return new AddNewStoreResponse();
    }

    /**
     * Create an instance of {@link AddNewUser }
     * 
     */
    public AddNewUser createAddNewUser() {
        return new AddNewUser();
    }

    /**
     * Create an instance of {@link AddNewUserResponse }
     * 
     */
    public AddNewUserResponse createAddNewUserResponse() {
        return new AddNewUserResponse();
    }

    /**
     * Create an instance of {@link AddToCart }
     * 
     */
    public AddToCart createAddToCart() {
        return new AddToCart();
    }

    /**
     * Create an instance of {@link AddToCartResponse }
     * 
     */
    public AddToCartResponse createAddToCartResponse() {
        return new AddToCartResponse();
    }

    /**
     * Create an instance of {@link DeleteCustomerReview }
     * 
     */
    public DeleteCustomerReview createDeleteCustomerReview() {
        return new DeleteCustomerReview();
    }

    /**
     * Create an instance of {@link DeleteCustomerReviewResponse }
     * 
     */
    public DeleteCustomerReviewResponse createDeleteCustomerReviewResponse() {
        return new DeleteCustomerReviewResponse();
    }

    /**
     * Create an instance of {@link DeleteFromCart }
     * 
     */
    public DeleteFromCart createDeleteFromCart() {
        return new DeleteFromCart();
    }

    /**
     * Create an instance of {@link DeleteFromCartResponse }
     * 
     */
    public DeleteFromCartResponse createDeleteFromCartResponse() {
        return new DeleteFromCartResponse();
    }

    /**
     * Create an instance of {@link DeleteProduct }
     * 
     */
    public DeleteProduct createDeleteProduct() {
        return new DeleteProduct();
    }

    /**
     * Create an instance of {@link DeleteProductResponse }
     * 
     */
    public DeleteProductResponse createDeleteProductResponse() {
        return new DeleteProductResponse();
    }

    /**
     * Create an instance of {@link DeleteStore }
     * 
     */
    public DeleteStore createDeleteStore() {
        return new DeleteStore();
    }

    /**
     * Create an instance of {@link DeleteStoreResponse }
     * 
     */
    public DeleteStoreResponse createDeleteStoreResponse() {
        return new DeleteStoreResponse();
    }

    /**
     * Create an instance of {@link DeleteUser }
     * 
     */
    public DeleteUser createDeleteUser() {
        return new DeleteUser();
    }

    /**
     * Create an instance of {@link DeleteUserResponse }
     * 
     */
    public DeleteUserResponse createDeleteUserResponse() {
        return new DeleteUserResponse();
    }

    /**
     * Create an instance of {@link GetAllCustomerReviews }
     * 
     */
    public GetAllCustomerReviews createGetAllCustomerReviews() {
        return new GetAllCustomerReviews();
    }

    /**
     * Create an instance of {@link GetAllCustomerReviewsResponse }
     * 
     */
    public GetAllCustomerReviewsResponse createGetAllCustomerReviewsResponse() {
        return new GetAllCustomerReviewsResponse();
    }

    /**
     * Create an instance of {@link GetAllProducts }
     * 
     */
    public GetAllProducts createGetAllProducts() {
        return new GetAllProducts();
    }

    /**
     * Create an instance of {@link GetAllProductsAndPricesInStore }
     * 
     */
    public GetAllProductsAndPricesInStore createGetAllProductsAndPricesInStore() {
        return new GetAllProductsAndPricesInStore();
    }

    /**
     * Create an instance of {@link GetAllProductsAndPricesInStoreResponse }
     * 
     */
    public GetAllProductsAndPricesInStoreResponse createGetAllProductsAndPricesInStoreResponse() {
        return new GetAllProductsAndPricesInStoreResponse();
    }

    /**
     * Create an instance of {@link GetAllProductsByCategory }
     * 
     */
    public GetAllProductsByCategory createGetAllProductsByCategory() {
        return new GetAllProductsByCategory();
    }

    /**
     * Create an instance of {@link GetAllProductsByCategoryResponse }
     * 
     */
    public GetAllProductsByCategoryResponse createGetAllProductsByCategoryResponse() {
        return new GetAllProductsByCategoryResponse();
    }

    /**
     * Create an instance of {@link GetAllProductsByMaxPrice }
     * 
     */
    public GetAllProductsByMaxPrice createGetAllProductsByMaxPrice() {
        return new GetAllProductsByMaxPrice();
    }

    /**
     * Create an instance of {@link GetAllProductsByMaxPriceResponse }
     * 
     */
    public GetAllProductsByMaxPriceResponse createGetAllProductsByMaxPriceResponse() {
        return new GetAllProductsByMaxPriceResponse();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     * 
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

    /**
     * Create an instance of {@link GetAllStores }
     * 
     */
    public GetAllStores createGetAllStores() {
        return new GetAllStores();
    }

    /**
     * Create an instance of {@link GetAllStoresAndPricesBySpecificProduct }
     * 
     */
    public GetAllStoresAndPricesBySpecificProduct createGetAllStoresAndPricesBySpecificProduct() {
        return new GetAllStoresAndPricesBySpecificProduct();
    }

    /**
     * Create an instance of {@link GetAllStoresAndPricesBySpecificProductResponse }
     * 
     */
    public GetAllStoresAndPricesBySpecificProductResponse createGetAllStoresAndPricesBySpecificProductResponse() {
        return new GetAllStoresAndPricesBySpecificProductResponse();
    }

    /**
     * Create an instance of {@link GetAllStoresResponse }
     * 
     */
    public GetAllStoresResponse createGetAllStoresResponse() {
        return new GetAllStoresResponse();
    }

    /**
     * Create an instance of {@link GetAllUserIDsThatBoughtTheProduct }
     * 
     */
    public GetAllUserIDsThatBoughtTheProduct createGetAllUserIDsThatBoughtTheProduct() {
        return new GetAllUserIDsThatBoughtTheProduct();
    }

    /**
     * Create an instance of {@link GetAllUserIDsThatBoughtTheProductResponse }
     * 
     */
    public GetAllUserIDsThatBoughtTheProductResponse createGetAllUserIDsThatBoughtTheProductResponse() {
        return new GetAllUserIDsThatBoughtTheProductResponse();
    }

    /**
     * Create an instance of {@link GetAllUsers }
     * 
     */
    public GetAllUsers createGetAllUsers() {
        return new GetAllUsers();
    }

    /**
     * Create an instance of {@link GetAllUsersResponse }
     * 
     */
    public GetAllUsersResponse createGetAllUsersResponse() {
        return new GetAllUsersResponse();
    }

    /**
     * Create an instance of {@link GetAverageRatingOfProduct }
     * 
     */
    public GetAverageRatingOfProduct createGetAverageRatingOfProduct() {
        return new GetAverageRatingOfProduct();
    }

    /**
     * Create an instance of {@link GetAverageRatingOfProductResponse }
     * 
     */
    public GetAverageRatingOfProductResponse createGetAverageRatingOfProductResponse() {
        return new GetAverageRatingOfProductResponse();
    }

    /**
     * Create an instance of {@link GetCustomerReviewByID }
     * 
     */
    public GetCustomerReviewByID createGetCustomerReviewByID() {
        return new GetCustomerReviewByID();
    }

    /**
     * Create an instance of {@link GetCustomerReviewByIDResponse }
     * 
     */
    public GetCustomerReviewByIDResponse createGetCustomerReviewByIDResponse() {
        return new GetCustomerReviewByIDResponse();
    }

    /**
     * Create an instance of {@link GetProductByID }
     * 
     */
    public GetProductByID createGetProductByID() {
        return new GetProductByID();
    }

    /**
     * Create an instance of {@link GetProductByIDResponse }
     * 
     */
    public GetProductByIDResponse createGetProductByIDResponse() {
        return new GetProductByIDResponse();
    }

    /**
     * Create an instance of {@link GetProductIDsAndStoreIDsBought }
     * 
     */
    public GetProductIDsAndStoreIDsBought createGetProductIDsAndStoreIDsBought() {
        return new GetProductIDsAndStoreIDsBought();
    }

    /**
     * Create an instance of {@link GetProductIDsAndStoreIDsBoughtResponse }
     * 
     */
    public GetProductIDsAndStoreIDsBoughtResponse createGetProductIDsAndStoreIDsBoughtResponse() {
        return new GetProductIDsAndStoreIDsBoughtResponse();
    }

    /**
     * Create an instance of {@link GetProductIDsAndStoreIDsFromCart }
     * 
     */
    public GetProductIDsAndStoreIDsFromCart createGetProductIDsAndStoreIDsFromCart() {
        return new GetProductIDsAndStoreIDsFromCart();
    }

    /**
     * Create an instance of {@link GetProductIDsAndStoreIDsFromCartResponse }
     * 
     */
    public GetProductIDsAndStoreIDsFromCartResponse createGetProductIDsAndStoreIDsFromCartResponse() {
        return new GetProductIDsAndStoreIDsFromCartResponse();
    }

    /**
     * Create an instance of {@link GetStoreByID }
     * 
     */
    public GetStoreByID createGetStoreByID() {
        return new GetStoreByID();
    }

    /**
     * Create an instance of {@link GetStoreByIDResponse }
     * 
     */
    public GetStoreByIDResponse createGetStoreByIDResponse() {
        return new GetStoreByIDResponse();
    }

    /**
     * Create an instance of {@link GetUserByID }
     * 
     */
    public GetUserByID createGetUserByID() {
        return new GetUserByID();
    }

    /**
     * Create an instance of {@link GetUserByIDResponse }
     * 
     */
    public GetUserByIDResponse createGetUserByIDResponse() {
        return new GetUserByIDResponse();
    }

    /**
     * Create an instance of {@link LinkStoreAndProduct }
     * 
     */
    public LinkStoreAndProduct createLinkStoreAndProduct() {
        return new LinkStoreAndProduct();
    }

    /**
     * Create an instance of {@link LinkStoreAndProductResponse }
     * 
     */
    public LinkStoreAndProductResponse createLinkStoreAndProductResponse() {
        return new LinkStoreAndProductResponse();
    }

    /**
     * Create an instance of {@link PayForUserCart }
     * 
     */
    public PayForUserCart createPayForUserCart() {
        return new PayForUserCart();
    }

    /**
     * Create an instance of {@link PayForUserCartResponse }
     * 
     */
    public PayForUserCartResponse createPayForUserCartResponse() {
        return new PayForUserCartResponse();
    }

    /**
     * Create an instance of {@link UpdateCustomerReview }
     * 
     */
    public UpdateCustomerReview createUpdateCustomerReview() {
        return new UpdateCustomerReview();
    }

    /**
     * Create an instance of {@link UpdateCustomerReviewResponse }
     * 
     */
    public UpdateCustomerReviewResponse createUpdateCustomerReviewResponse() {
        return new UpdateCustomerReviewResponse();
    }

    /**
     * Create an instance of {@link UpdateProduct }
     * 
     */
    public UpdateProduct createUpdateProduct() {
        return new UpdateProduct();
    }

    /**
     * Create an instance of {@link UpdateProductResponse }
     * 
     */
    public UpdateProductResponse createUpdateProductResponse() {
        return new UpdateProductResponse();
    }

    /**
     * Create an instance of {@link UpdateStore }
     * 
     */
    public UpdateStore createUpdateStore() {
        return new UpdateStore();
    }

    /**
     * Create an instance of {@link UpdateStoreResponse }
     * 
     */
    public UpdateStoreResponse createUpdateStoreResponse() {
        return new UpdateStoreResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link CustomerReview }
     * 
     */
    public CustomerReview createCustomerReview() {
        return new CustomerReview();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Pair }
     * 
     */
    public Pair createPair() {
        return new Pair();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Store }
     * 
     */
    public Store createStore() {
        return new Store();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "DatabaseException")
    public JAXBElement<DatabaseException> createDatabaseException(DatabaseException value) {
        return new JAXBElement<DatabaseException>(_DatabaseException_QNAME, DatabaseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCustomerReview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewCustomerReview")
    public JAXBElement<AddNewCustomerReview> createAddNewCustomerReview(AddNewCustomerReview value) {
        return new JAXBElement<AddNewCustomerReview>(_AddNewCustomerReview_QNAME, AddNewCustomerReview.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCustomerReviewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewCustomerReviewResponse")
    public JAXBElement<AddNewCustomerReviewResponse> createAddNewCustomerReviewResponse(AddNewCustomerReviewResponse value) {
        return new JAXBElement<AddNewCustomerReviewResponse>(_AddNewCustomerReviewResponse_QNAME, AddNewCustomerReviewResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewProduct")
    public JAXBElement<AddNewProduct> createAddNewProduct(AddNewProduct value) {
        return new JAXBElement<AddNewProduct>(_AddNewProduct_QNAME, AddNewProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewProductResponse")
    public JAXBElement<AddNewProductResponse> createAddNewProductResponse(AddNewProductResponse value) {
        return new JAXBElement<AddNewProductResponse>(_AddNewProductResponse_QNAME, AddNewProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewStore }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewStore")
    public JAXBElement<AddNewStore> createAddNewStore(AddNewStore value) {
        return new JAXBElement<AddNewStore>(_AddNewStore_QNAME, AddNewStore.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewStoreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewStoreResponse")
    public JAXBElement<AddNewStoreResponse> createAddNewStoreResponse(AddNewStoreResponse value) {
        return new JAXBElement<AddNewStoreResponse>(_AddNewStoreResponse_QNAME, AddNewStoreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewUser")
    public JAXBElement<AddNewUser> createAddNewUser(AddNewUser value) {
        return new JAXBElement<AddNewUser>(_AddNewUser_QNAME, AddNewUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addNewUserResponse")
    public JAXBElement<AddNewUserResponse> createAddNewUserResponse(AddNewUserResponse value) {
        return new JAXBElement<AddNewUserResponse>(_AddNewUserResponse_QNAME, AddNewUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddToCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addToCart")
    public JAXBElement<AddToCart> createAddToCart(AddToCart value) {
        return new JAXBElement<AddToCart>(_AddToCart_QNAME, AddToCart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddToCartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "addToCartResponse")
    public JAXBElement<AddToCartResponse> createAddToCartResponse(AddToCartResponse value) {
        return new JAXBElement<AddToCartResponse>(_AddToCartResponse_QNAME, AddToCartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCustomerReview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteCustomerReview")
    public JAXBElement<DeleteCustomerReview> createDeleteCustomerReview(DeleteCustomerReview value) {
        return new JAXBElement<DeleteCustomerReview>(_DeleteCustomerReview_QNAME, DeleteCustomerReview.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCustomerReviewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteCustomerReviewResponse")
    public JAXBElement<DeleteCustomerReviewResponse> createDeleteCustomerReviewResponse(DeleteCustomerReviewResponse value) {
        return new JAXBElement<DeleteCustomerReviewResponse>(_DeleteCustomerReviewResponse_QNAME, DeleteCustomerReviewResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFromCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteFromCart")
    public JAXBElement<DeleteFromCart> createDeleteFromCart(DeleteFromCart value) {
        return new JAXBElement<DeleteFromCart>(_DeleteFromCart_QNAME, DeleteFromCart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFromCartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteFromCartResponse")
    public JAXBElement<DeleteFromCartResponse> createDeleteFromCartResponse(DeleteFromCartResponse value) {
        return new JAXBElement<DeleteFromCartResponse>(_DeleteFromCartResponse_QNAME, DeleteFromCartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteProduct")
    public JAXBElement<DeleteProduct> createDeleteProduct(DeleteProduct value) {
        return new JAXBElement<DeleteProduct>(_DeleteProduct_QNAME, DeleteProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteProductResponse")
    public JAXBElement<DeleteProductResponse> createDeleteProductResponse(DeleteProductResponse value) {
        return new JAXBElement<DeleteProductResponse>(_DeleteProductResponse_QNAME, DeleteProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteStore }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteStore")
    public JAXBElement<DeleteStore> createDeleteStore(DeleteStore value) {
        return new JAXBElement<DeleteStore>(_DeleteStore_QNAME, DeleteStore.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteStoreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteStoreResponse")
    public JAXBElement<DeleteStoreResponse> createDeleteStoreResponse(DeleteStoreResponse value) {
        return new JAXBElement<DeleteStoreResponse>(_DeleteStoreResponse_QNAME, DeleteStoreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteUser")
    public JAXBElement<DeleteUser> createDeleteUser(DeleteUser value) {
        return new JAXBElement<DeleteUser>(_DeleteUser_QNAME, DeleteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "deleteUserResponse")
    public JAXBElement<DeleteUserResponse> createDeleteUserResponse(DeleteUserResponse value) {
        return new JAXBElement<DeleteUserResponse>(_DeleteUserResponse_QNAME, DeleteUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCustomerReviews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllCustomerReviews")
    public JAXBElement<GetAllCustomerReviews> createGetAllCustomerReviews(GetAllCustomerReviews value) {
        return new JAXBElement<GetAllCustomerReviews>(_GetAllCustomerReviews_QNAME, GetAllCustomerReviews.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCustomerReviewsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllCustomerReviewsResponse")
    public JAXBElement<GetAllCustomerReviewsResponse> createGetAllCustomerReviewsResponse(GetAllCustomerReviewsResponse value) {
        return new JAXBElement<GetAllCustomerReviewsResponse>(_GetAllCustomerReviewsResponse_QNAME, GetAllCustomerReviewsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProducts")
    public JAXBElement<GetAllProducts> createGetAllProducts(GetAllProducts value) {
        return new JAXBElement<GetAllProducts>(_GetAllProducts_QNAME, GetAllProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsAndPricesInStore }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsAndPricesInStore")
    public JAXBElement<GetAllProductsAndPricesInStore> createGetAllProductsAndPricesInStore(GetAllProductsAndPricesInStore value) {
        return new JAXBElement<GetAllProductsAndPricesInStore>(_GetAllProductsAndPricesInStore_QNAME, GetAllProductsAndPricesInStore.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsAndPricesInStoreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsAndPricesInStoreResponse")
    public JAXBElement<GetAllProductsAndPricesInStoreResponse> createGetAllProductsAndPricesInStoreResponse(GetAllProductsAndPricesInStoreResponse value) {
        return new JAXBElement<GetAllProductsAndPricesInStoreResponse>(_GetAllProductsAndPricesInStoreResponse_QNAME, GetAllProductsAndPricesInStoreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsByCategory")
    public JAXBElement<GetAllProductsByCategory> createGetAllProductsByCategory(GetAllProductsByCategory value) {
        return new JAXBElement<GetAllProductsByCategory>(_GetAllProductsByCategory_QNAME, GetAllProductsByCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsByCategoryResponse")
    public JAXBElement<GetAllProductsByCategoryResponse> createGetAllProductsByCategoryResponse(GetAllProductsByCategoryResponse value) {
        return new JAXBElement<GetAllProductsByCategoryResponse>(_GetAllProductsByCategoryResponse_QNAME, GetAllProductsByCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByMaxPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsByMaxPrice")
    public JAXBElement<GetAllProductsByMaxPrice> createGetAllProductsByMaxPrice(GetAllProductsByMaxPrice value) {
        return new JAXBElement<GetAllProductsByMaxPrice>(_GetAllProductsByMaxPrice_QNAME, GetAllProductsByMaxPrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByMaxPriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsByMaxPriceResponse")
    public JAXBElement<GetAllProductsByMaxPriceResponse> createGetAllProductsByMaxPriceResponse(GetAllProductsByMaxPriceResponse value) {
        return new JAXBElement<GetAllProductsByMaxPriceResponse>(_GetAllProductsByMaxPriceResponse_QNAME, GetAllProductsByMaxPriceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllProductsResponse")
    public JAXBElement<GetAllProductsResponse> createGetAllProductsResponse(GetAllProductsResponse value) {
        return new JAXBElement<GetAllProductsResponse>(_GetAllProductsResponse_QNAME, GetAllProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllStores")
    public JAXBElement<GetAllStores> createGetAllStores(GetAllStores value) {
        return new JAXBElement<GetAllStores>(_GetAllStores_QNAME, GetAllStores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStoresAndPricesBySpecificProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllStoresAndPricesBySpecificProduct")
    public JAXBElement<GetAllStoresAndPricesBySpecificProduct> createGetAllStoresAndPricesBySpecificProduct(GetAllStoresAndPricesBySpecificProduct value) {
        return new JAXBElement<GetAllStoresAndPricesBySpecificProduct>(_GetAllStoresAndPricesBySpecificProduct_QNAME, GetAllStoresAndPricesBySpecificProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStoresAndPricesBySpecificProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllStoresAndPricesBySpecificProductResponse")
    public JAXBElement<GetAllStoresAndPricesBySpecificProductResponse> createGetAllStoresAndPricesBySpecificProductResponse(GetAllStoresAndPricesBySpecificProductResponse value) {
        return new JAXBElement<GetAllStoresAndPricesBySpecificProductResponse>(_GetAllStoresAndPricesBySpecificProductResponse_QNAME, GetAllStoresAndPricesBySpecificProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStoresResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllStoresResponse")
    public JAXBElement<GetAllStoresResponse> createGetAllStoresResponse(GetAllStoresResponse value) {
        return new JAXBElement<GetAllStoresResponse>(_GetAllStoresResponse_QNAME, GetAllStoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUserIDsThatBoughtTheProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllUserIDsThatBoughtTheProduct")
    public JAXBElement<GetAllUserIDsThatBoughtTheProduct> createGetAllUserIDsThatBoughtTheProduct(GetAllUserIDsThatBoughtTheProduct value) {
        return new JAXBElement<GetAllUserIDsThatBoughtTheProduct>(_GetAllUserIDsThatBoughtTheProduct_QNAME, GetAllUserIDsThatBoughtTheProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUserIDsThatBoughtTheProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllUserIDsThatBoughtTheProductResponse")
    public JAXBElement<GetAllUserIDsThatBoughtTheProductResponse> createGetAllUserIDsThatBoughtTheProductResponse(GetAllUserIDsThatBoughtTheProductResponse value) {
        return new JAXBElement<GetAllUserIDsThatBoughtTheProductResponse>(_GetAllUserIDsThatBoughtTheProductResponse_QNAME, GetAllUserIDsThatBoughtTheProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllUsers")
    public JAXBElement<GetAllUsers> createGetAllUsers(GetAllUsers value) {
        return new JAXBElement<GetAllUsers>(_GetAllUsers_QNAME, GetAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAllUsersResponse")
    public JAXBElement<GetAllUsersResponse> createGetAllUsersResponse(GetAllUsersResponse value) {
        return new JAXBElement<GetAllUsersResponse>(_GetAllUsersResponse_QNAME, GetAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAverageRatingOfProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAverageRatingOfProduct")
    public JAXBElement<GetAverageRatingOfProduct> createGetAverageRatingOfProduct(GetAverageRatingOfProduct value) {
        return new JAXBElement<GetAverageRatingOfProduct>(_GetAverageRatingOfProduct_QNAME, GetAverageRatingOfProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAverageRatingOfProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getAverageRatingOfProductResponse")
    public JAXBElement<GetAverageRatingOfProductResponse> createGetAverageRatingOfProductResponse(GetAverageRatingOfProductResponse value) {
        return new JAXBElement<GetAverageRatingOfProductResponse>(_GetAverageRatingOfProductResponse_QNAME, GetAverageRatingOfProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerReviewByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getCustomerReviewByID")
    public JAXBElement<GetCustomerReviewByID> createGetCustomerReviewByID(GetCustomerReviewByID value) {
        return new JAXBElement<GetCustomerReviewByID>(_GetCustomerReviewByID_QNAME, GetCustomerReviewByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerReviewByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getCustomerReviewByIDResponse")
    public JAXBElement<GetCustomerReviewByIDResponse> createGetCustomerReviewByIDResponse(GetCustomerReviewByIDResponse value) {
        return new JAXBElement<GetCustomerReviewByIDResponse>(_GetCustomerReviewByIDResponse_QNAME, GetCustomerReviewByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getProductByID")
    public JAXBElement<GetProductByID> createGetProductByID(GetProductByID value) {
        return new JAXBElement<GetProductByID>(_GetProductByID_QNAME, GetProductByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getProductByIDResponse")
    public JAXBElement<GetProductByIDResponse> createGetProductByIDResponse(GetProductByIDResponse value) {
        return new JAXBElement<GetProductByIDResponse>(_GetProductByIDResponse_QNAME, GetProductByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductIDsAndStoreIDsBought }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getProductIDsAndStoreIDsBought")
    public JAXBElement<GetProductIDsAndStoreIDsBought> createGetProductIDsAndStoreIDsBought(GetProductIDsAndStoreIDsBought value) {
        return new JAXBElement<GetProductIDsAndStoreIDsBought>(_GetProductIDsAndStoreIDsBought_QNAME, GetProductIDsAndStoreIDsBought.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductIDsAndStoreIDsBoughtResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getProductIDsAndStoreIDsBoughtResponse")
    public JAXBElement<GetProductIDsAndStoreIDsBoughtResponse> createGetProductIDsAndStoreIDsBoughtResponse(GetProductIDsAndStoreIDsBoughtResponse value) {
        return new JAXBElement<GetProductIDsAndStoreIDsBoughtResponse>(_GetProductIDsAndStoreIDsBoughtResponse_QNAME, GetProductIDsAndStoreIDsBoughtResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductIDsAndStoreIDsFromCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getProductIDsAndStoreIDsFromCart")
    public JAXBElement<GetProductIDsAndStoreIDsFromCart> createGetProductIDsAndStoreIDsFromCart(GetProductIDsAndStoreIDsFromCart value) {
        return new JAXBElement<GetProductIDsAndStoreIDsFromCart>(_GetProductIDsAndStoreIDsFromCart_QNAME, GetProductIDsAndStoreIDsFromCart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductIDsAndStoreIDsFromCartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getProductIDsAndStoreIDsFromCartResponse")
    public JAXBElement<GetProductIDsAndStoreIDsFromCartResponse> createGetProductIDsAndStoreIDsFromCartResponse(GetProductIDsAndStoreIDsFromCartResponse value) {
        return new JAXBElement<GetProductIDsAndStoreIDsFromCartResponse>(_GetProductIDsAndStoreIDsFromCartResponse_QNAME, GetProductIDsAndStoreIDsFromCartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStoreByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getStoreByID")
    public JAXBElement<GetStoreByID> createGetStoreByID(GetStoreByID value) {
        return new JAXBElement<GetStoreByID>(_GetStoreByID_QNAME, GetStoreByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStoreByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getStoreByIDResponse")
    public JAXBElement<GetStoreByIDResponse> createGetStoreByIDResponse(GetStoreByIDResponse value) {
        return new JAXBElement<GetStoreByIDResponse>(_GetStoreByIDResponse_QNAME, GetStoreByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getUserByID")
    public JAXBElement<GetUserByID> createGetUserByID(GetUserByID value) {
        return new JAXBElement<GetUserByID>(_GetUserByID_QNAME, GetUserByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "getUserByIDResponse")
    public JAXBElement<GetUserByIDResponse> createGetUserByIDResponse(GetUserByIDResponse value) {
        return new JAXBElement<GetUserByIDResponse>(_GetUserByIDResponse_QNAME, GetUserByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkStoreAndProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "linkStoreAndProduct")
    public JAXBElement<LinkStoreAndProduct> createLinkStoreAndProduct(LinkStoreAndProduct value) {
        return new JAXBElement<LinkStoreAndProduct>(_LinkStoreAndProduct_QNAME, LinkStoreAndProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkStoreAndProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "linkStoreAndProductResponse")
    public JAXBElement<LinkStoreAndProductResponse> createLinkStoreAndProductResponse(LinkStoreAndProductResponse value) {
        return new JAXBElement<LinkStoreAndProductResponse>(_LinkStoreAndProductResponse_QNAME, LinkStoreAndProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayForUserCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "payForUserCart")
    public JAXBElement<PayForUserCart> createPayForUserCart(PayForUserCart value) {
        return new JAXBElement<PayForUserCart>(_PayForUserCart_QNAME, PayForUserCart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayForUserCartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "payForUserCartResponse")
    public JAXBElement<PayForUserCartResponse> createPayForUserCartResponse(PayForUserCartResponse value) {
        return new JAXBElement<PayForUserCartResponse>(_PayForUserCartResponse_QNAME, PayForUserCartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCustomerReview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateCustomerReview")
    public JAXBElement<UpdateCustomerReview> createUpdateCustomerReview(UpdateCustomerReview value) {
        return new JAXBElement<UpdateCustomerReview>(_UpdateCustomerReview_QNAME, UpdateCustomerReview.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCustomerReviewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateCustomerReviewResponse")
    public JAXBElement<UpdateCustomerReviewResponse> createUpdateCustomerReviewResponse(UpdateCustomerReviewResponse value) {
        return new JAXBElement<UpdateCustomerReviewResponse>(_UpdateCustomerReviewResponse_QNAME, UpdateCustomerReviewResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateProduct")
    public JAXBElement<UpdateProduct> createUpdateProduct(UpdateProduct value) {
        return new JAXBElement<UpdateProduct>(_UpdateProduct_QNAME, UpdateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateProductResponse")
    public JAXBElement<UpdateProductResponse> createUpdateProductResponse(UpdateProductResponse value) {
        return new JAXBElement<UpdateProductResponse>(_UpdateProductResponse_QNAME, UpdateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateStore }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateStore")
    public JAXBElement<UpdateStore> createUpdateStore(UpdateStore value) {
        return new JAXBElement<UpdateStore>(_UpdateStore_QNAME, UpdateStore.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateStoreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateStoreResponse")
    public JAXBElement<UpdateStoreResponse> createUpdateStoreResponse(UpdateStoreResponse value) {
        return new JAXBElement<UpdateStoreResponse>(_UpdateStoreResponse_QNAME, UpdateStoreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.com/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

}
