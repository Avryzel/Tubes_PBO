// Class Contact
public class Contact {
    private String Nama;
    private String NoHP;
    private String Pesan;

    public Contact(String Nama, String NoHP) {
        this.Nama = Nama;
        this.NoHP = NoHP;
        this.Pesan = "";
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getNoHP() {
        return NoHP;
    }

    public void setNoHP(String NoHP) {
        this.NoHP = NoHP;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String Pesan) {
        this.Pesan = Pesan;
    }

    @Override
    public String toString() {
        return Nama + " (" + NoHP + ")";
    }
}
