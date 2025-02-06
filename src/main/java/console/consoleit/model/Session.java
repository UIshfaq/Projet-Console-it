package console.consoleit.model;


public class Session {
    private static int idEmploye; // Stocke l'ID de l'utilisateur connecté

    public static void setIdEmploye(int id) {
        idEmploye = id; // Définit l'ID quand l'utilisateur se connecte
    }

    public static int getIdEmploye() {
        return idEmploye; // Récupère l'ID stocké
    }
}
