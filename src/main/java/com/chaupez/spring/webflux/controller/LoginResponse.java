package com.chaupez.spring.webflux.controller;
 
public record LoginResponse(String token, Boolean status, String message) {
}