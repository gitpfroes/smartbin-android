package br.pucminas.smartbin.model;

public enum TipoLixeira {
    PLASTICO("Plastico"),
    PAPEL("Papel"),
    VIDRO("Vidro"),
    METAL("Metal")
    ;

    private final String text;

    /**
     * @param text
     */
    TipoLixeira(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
