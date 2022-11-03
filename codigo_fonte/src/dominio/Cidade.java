package dominio;

public class Cidade {
    
    private float latitude;
    private float longitude;
    private String nomeCidade;

    public Cidade(float latitude, float longitude, String nomeCidade) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomeCidade = nomeCidade;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }


}