package bd1.obli2012.gui.backend;

import bd1.obli2012.versionado.BDDVersionado;
import bd1.obli2012.versionado.Cambio;
import bd1.obli2012.versionado.VersionBDD;
import bd1.obli2012.versionado.helper.VersionadoHelper;
import java.util.ArrayList;
import java.util.List;

public class Contexto {

    private static Contexto instancia;
    private BDDVersionado bddVersionado;
    private List<Cambio> colaCambios;
    private boolean hayCambios = false;

    public static Contexto getInstance() {
        if (instancia == null) {
            instancia = new Contexto();
        }
        return instancia;
    }

    public BDDVersionado getBDDVersionado() {
        return this.bddVersionado;
    }

    public List<Cambio> getColaCambios() {
        return this.colaCambios;
    }

    private Contexto() {
    }

    public void seleccionarBaseDeDatos(String nombreBaseDeDatos) {
        if (bddVersionado != null) {
            if (!bddVersionado.getNombreBDD().equals(nombreBaseDeDatos)) {
                guardarCambiosEnCola();
                this.bddVersionado = VersionadoHelper.obtenerVersiones(nombreBaseDeDatos);
                this.colaCambios = new ArrayList<Cambio>();
                this.hayCambios = false;
            }
        } else {
            this.bddVersionado = VersionadoHelper.obtenerVersiones(nombreBaseDeDatos);
            this.colaCambios = new ArrayList<Cambio>();
            this.hayCambios = false;
        }
    }

    public void guardarCambioACola(Cambio cambio) {
        if (this.colaCambios == null) {
            this.colaCambios = new ArrayList<Cambio>();
        }
        this.colaCambios.add(cambio);
        this.hayCambios = true;
    }

    private void guardarCambiosEnCola() {
        if (this.colaCambios != null && !this.colaCambios.isEmpty()) {
            Integer numVersion = bddVersionado.getVersionActual() + 1;

            VersionBDD version = new VersionBDD();
            version.setVersion(numVersion);

            List<Cambio> cambios = new ArrayList<Cambio>();
            cambios.addAll(this.colaCambios);
            version.setCambios(cambios);
            bddVersionado.getVersiones().put(numVersion, version);
            bddVersionado.setVersionActual(numVersion);
            VersionadoHelper.salvarCambios(bddVersionado);
            this.colaCambios = new ArrayList<Cambio>();
            this.hayCambios = false;
        }
    }
    
    public boolean hayCambios(){
        return this.hayCambios;
    }
}