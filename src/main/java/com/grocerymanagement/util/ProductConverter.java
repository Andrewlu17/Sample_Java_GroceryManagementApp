package com.grocerymanagement.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.logging.Logger;

import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.model.Product;
import com.grocerymanagement.model.ProductList;

public class ProductConverter {
	public static Logger log = Logger.getLogger(ProductConverter.class);

	public static Product productDTOToProduct(ProductDTO productDTO, Product product) {
		product.setCategoryImage(productDTO.getCategoryImage());

		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setProductId(productDTO.getProductId());
		product.setProductImage(productDTO.getProductImage());
		product.setEncodedStringProductImg(productDTO.getEncodedStringProductImg());
		product.setProductName(productDTO.getProductName());

		product.setImagePath(productDTO.getImagePath());

		return product;

	}

	public static ProductDTO productToProductDTO(Product product, ProductDTO productDTO) {
		productDTO.setCategoryImage(product.getCategoryImage());
		// productDTO.setCategoryList(null);
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductId(product.getProductId());
		// productDTO.setProductImage(product.getProductImage());
		productDTO.setEncodedStringProductImg(product.getEncodedStringProductImg());
		productDTO.setImagePath(product.getImagePath());

		productDTO.setProductName(product.getProductName());
		// productDTO.setSubCategoryList(null);
		// productDTO.setUser(null);
		return productDTO;
	}

	public static List<ProductDTO> productListToProductDTOList(List<Product> productList,
			List<ProductDTO> productDTOList) {
		if (productDTOList == null)
			productDTOList = new ArrayList<ProductDTO>();
		Iterator productListIterator = productList.iterator();
		while (productListIterator.hasNext()) {
			Product product = (Product) productListIterator.next();
			productDTOList.add(productToProductDTO(product, new ProductDTO()));
		}

		return productDTOList;
	}

	public static Set<ProductList> productDTOListToProductList(Map<Long, ProductListDTO> productDTOList,
			Set<ProductList> productList) {
		if (productList == null)
			productList = new HashSet<ProductList>();
		Iterator productListIterator = productDTOList.keySet().iterator();
		while (productListIterator.hasNext()) {
			Long key = (Long) productListIterator.next();
			productList.add(productListDTOToProductListConversion(productDTOList.get(key), new ProductList()));
		}

		return productList;
	}

	public static ProductListDTO productListToProductDTOListConversion(ProductList productList,
			ProductListDTO productDTOList) {
		List productDTOList1 = new ArrayList<ProductDTO>();
		if (productDTOList == null)
			productDTOList = new ProductListDTO();
		if (productList != null) {
			List<Product> prodlist = productList.getProductList();
			log.info(prodlist);
			Iterator productListIterator = prodlist.iterator();
			while (productListIterator.hasNext()) {
				Product product = (Product) productListIterator.next();
				productDTOList1.add(productToProductDTO(product, new ProductDTO()));
			}
			productDTOList.setListId(productList.getItemlistId());
			productDTOList.setListName(productList.getListName());
			productDTOList.setProductList(productDTOList1);
		}
		return productDTOList;
	}

	public static ProductList productListDTOToProductListConversion(ProductListDTO productListDTO,
			ProductList productList) {
		List<Product> productList1 = new ArrayList<Product>();
		;
		if (productList == null)
			productList = new ProductList();
		List<ProductDTO> prodlist = productListDTO.getProductList();
		log.info(prodlist);
		Iterator productListIterator = prodlist.iterator();
		while (productListIterator.hasNext()) {
			ProductDTO productDTO = (ProductDTO) productListIterator.next();
			productList1.add(productDTOToProduct(productDTO, new Product()));
		}
		productList.setItemlistId(productListDTO.getListId());
		productList.setListName(productListDTO.getListName());
		productList.setProductList(productList1);
		return productList;
	}

	public static Product productToProduct(Product inproduct, Product outproduct) {
		outproduct.setCategoryImage(inproduct.getCategoryImage());
		// productDTO.setCategoryList(null);
		outproduct.setDescription(inproduct.getDescription());
		outproduct.setPrice(inproduct.getPrice());
		outproduct.setProductId(inproduct.getProductId());
		// productDTO.setProductImage(product.getProductImage());
		outproduct.setEncodedStringProductImg(inproduct.getEncodedStringProductImg());
		outproduct.setImagePath(inproduct.getImagePath());
		if (outproduct.getCreateDate() == null)
			outproduct.setCreateDate(inproduct.getCreateDate());
		outproduct.setUpdateDate(inproduct.getUpdateDate());
		outproduct.setProductName(inproduct.getProductName());
		// productDTO.setSubCategoryList(null);
		// productDTO.setUser(null);
		return outproduct;
	}

}
