package com.grocerymanagement.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.exceptions.DAOException;
import com.grocerymanagement.model.Product;
import com.grocerymanagement.model.ProductList;
import com.grocerymanagement.model.User;
import com.grocerymanagement.service.UserService;

public class ProductListConverter  {
	@Autowired
	UserService userDAO;
	
	public static ProductList convertDTOtoBean(ProductListDTO dto, User user) throws DAOException
	{
		ProductList productList=new ProductList();
		productList.setListName(dto.getListName());
		
		
		return productList;
	}

	public static ProductList convertDTOtoBean(ProductListDTO dto, ProductList productList, User user) throws DAOException
	{
		if(productList==null)
		productList=new ProductList();
		productList.setUser(user);
		productList.setListName(dto.getListName());
		productList.setItemlistId(dto.getListId());
		List<Product> productListToSave=new ArrayList<Product>();
		if(dto.getProductList()!=null)
		for(ProductDTO product : dto.getProductList())
		{
			 
			productListToSave.add(ProductConverter.productDTOToProduct(product, new Product()));
		}
		
		productList.setProductList(productListToSave);
		return productList;
	}

	public static List<Product> convertDTOListtoBeanList(List<ProductDTO> dto)
	{
		List<Product> productList=new ArrayList<Product>();
		Iterator itr=dto.iterator();
		while(itr.hasNext())
		{
			ProductDTO productDTO = (ProductDTO) itr.next();
			Product product=convertProductDTOtoProductBean(productDTO);
			productList.add(product);
		}
		return productList;
	}
	public static Product convertProductDTOtoProductBean(ProductDTO dto)
	{
		Product product =new Product();
		product.setProductId(dto.getProductId());
		product.setProductName(dto.getProductName());
		return product;
	}
	
}
