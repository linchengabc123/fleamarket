package com.fleamarket.common.pojo;

public class OrderItem {
 private Long id;
 private String orderId;
 private String productId;
 private Integer num;
 private String productImage;
 private String productName;
 private Double productPrice;
 private String orderReceiverinfo;
 private Integer orderPaystate;
 public Integer getOrderPaystate() {
  return orderPaystate;
 }
 public void setOrderPaystate(Integer orderPaystate) {
  this.orderPaystate = orderPaystate;
 }
 public String getOrderReceiverinfo() {
  return orderReceiverinfo;
 }
 public void setOrderReceiverinfo(String orderReceiverinfo) {
  this.orderReceiverinfo = orderReceiverinfo;
 }
 public Long getId() {
  return id;
 }
 public void setId(Long id) {
  this.id = id;
 }
 public String getOrderId() {
  return orderId;
 }
 public void setOrderId(String orderId) {
  this.orderId = orderId;
 }
 public String getProductId() {
  return productId;
 }
 public void setProductId(String productId) {
  this.productId = productId;
 }
 public Integer getNum() {
  return num;
 }
 public void setNum(Integer num) {
  this.num = num;
 }
 public String getProductImage() {
  return productImage;
 }
 public void setProductImage(String productImage) {
  this.productImage = productImage;
 }
 public String getProductName() {
  return productName;
 }
 public void setProductName(String productName) {
  this.productName = productName;
 }
 public Double getProductPrice() {
  return productPrice;
 }
 public void setProductPrice(Double productPrice) {
  this.productPrice = productPrice;
 }
}