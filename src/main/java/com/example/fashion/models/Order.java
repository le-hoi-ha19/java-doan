    package com.example.fashion.models;

    import java.time.LocalDate;
    import java.util.Set;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;

    @Entity
    @Table(name = ("Orders"))
    public class Order {
        @Id
        @Column(name = "OrderID")
        // @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long OrderID;
        @Column(name = "OrderDate")
        private LocalDate OrderDate;
        @Column(name = "DeliveryDate")
        private LocalDate DeliveryDate;
        @Column(name = "TotalPrice")
        private Double TotalPrice;
        @Column(name = "ShippingFee")
        private Double ShippingFee;
        @Column(name = "OrderStatus")
        private String OrderStatus;
        @Column(name = "Notes")
        private String Notes;
        @OneToMany(mappedBy= "orders")
        private Set<OrderDetail> orderDetails;
        @ManyToOne
        @JoinColumn(name = "userId", referencedColumnName = "id")
        private User user;

    public Long getOrderID() {
        return this.OrderID;
    }

    public void setOrderID(Long OrderID) {
        this.OrderID = OrderID;
    }

    public LocalDate getOrderDate() {
        return this.OrderDate;
    }

    public void setOrderDate(LocalDate OrderDate) {
        this.OrderDate = OrderDate;
    }

    public LocalDate getDeliveryDate() {
        return this.DeliveryDate;
    }

    public void setDeliveryDate(LocalDate DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public Double getTotalPrice() {
        return this.TotalPrice;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Double getShippingFee() {
        return this.ShippingFee;
    }

    public void setShippingFee(Double ShippingFee) {
        this.ShippingFee = ShippingFee;
    }

    public String getOrderStatus() {
        return this.OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getNotes() {
        return this.Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public Set<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

        

        public Order(Long OrderID, LocalDate OrderDate, LocalDate DeliveryDate, Double TotalPrice, Double ShippingFee, String OrderStatus, String Notes, Set<OrderDetail> orderDetails, User user) {
            super();
            this.OrderID = OrderID;
            this.OrderDate = OrderDate;
            this.DeliveryDate = DeliveryDate;
            this.TotalPrice = TotalPrice;
            this.ShippingFee = ShippingFee;
            this.OrderStatus = OrderStatus;
            this.Notes = Notes;
            this.orderDetails = orderDetails;
            this.user = user;
        }

        public Order() {

        }

        
    }
