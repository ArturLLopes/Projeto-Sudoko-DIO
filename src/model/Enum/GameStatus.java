package model.Enum;

public enum GameStatus {

    NON_STARTED("não iniciado"),
    INCOMPLETE("Iniciado"),
    COMPLETE("Completo");

    private String label;

    public String getLabel() {
        return label;
    }

    GameStatus(final String label){
        this.label = label;
    }
}
