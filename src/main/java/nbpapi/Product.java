package nbpapi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    @XmlTransient
    private int productId;
    private String productName;
    private String postingDate;
    private double priceUSD;
    private double pricePLN;

    public Product(String productName, String postingDate, double priceUSD, double pricePLN) {
        this.productName = productName;
        this.postingDate = postingDate;
        this.priceUSD = priceUSD;
        this.pricePLN = pricePLN;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public double getPricePLN() {
        return pricePLN;
    }

    public void setPricePLN(double pricePLN) {
        this.pricePLN = pricePLN;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", postingDate='" + postingDate + '\'' +
                ", priceUSD=" + priceUSD +
                ", pricePLN=" + pricePLN +
                '}';
    }
}
