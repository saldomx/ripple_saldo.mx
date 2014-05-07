package com.mxinteligente.controller.ventas;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.apache.commons.lang.StringUtils.leftPad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import org.zkoss.zul.Combobox;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.mxinteligente.infra.PathContext;
import com.mxinteligente.infra.ThumbNail;
import com.mxinteligente.model.entidades.Catproductos;
import com.mxinteligente.model.entidades.Producto;
import com.mxinteligente.model.entidades.Usuarios;
import com.mxinteligente.model.negocio.GeneralServI;
import com.mxinteligente.model.negocio.VentasServI;

public class AltaProducto extends GenericForwardComposer {

	private Textbox textNombre, textCodigo, textDescripcion;
	private Combobox CmbCategorias;
	private Window winprod;
	private Textbox decPrecio;
	private static final int FILE_SIZE = 100;// 100k
	private static String SAVE_PATH = "\\";
	Fileupload btnCargarImg;
	Image image1, image2, image3;
	private Usuarios usuario;
	private ListModelList ModelCategorias;
	private List categorias;



	
	static Logger logger = LoggerFactory.getLogger(AltaProducto.class);

	@Autowired(required=true)
	private GeneralServI generalServ;
	
	@Autowired(required=true)
	private VentasServI ventasServ;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		SAVE_PATH = PathContext.getImgPath();
		Integer id = (Integer)arg.get("usrId");
		usuario = new Usuarios();
		usuario.setId(id);
		
		cargarCategoriaProductos();
		
	}

	public void cargarCategoriaProductos(){	

		List<Catproductos> lstCat = ventasServ.obtenerCategoriasProductos();
		
		if(!lstCat.isEmpty()){
			ModelCategorias = new ListModelList(lstCat);
			CmbCategorias.setItemRenderer(new ComboRender());
			CmbCategorias.setModel(ModelCategorias);
			CmbCategorias.addEventListener("onInitRenderLater", this);
			CmbCategorias.setValue("");
		}
		
	}
	
public void onClick$btnGuardar() throws Exception{
	try{
	Producto prod = new Producto();
	prod.setUsers(usuario);
	prod.setCode(textCodigo.getValue());
	prod.setDescription(textDescripcion.getValue());
	prod.setName(textNombre.getValue());
	prod.setPrice(new BigDecimal(decPrecio.getValue()));
	prod.setCategoria(new Catproductos());
	prod.getCategoria().setId(((Integer)CmbCategorias.getSelectedItem().getValue()));
	String nombreArchivo;
	
	if(image1.getContent()!=null && image1.isVisible()){
		nombreArchivo = new RandomAlphaNum().gen(5)+prod.getCode();	
		Media media = image1.getContent();
		String tipo = media.getName().substring(media.getName().indexOf("."));
		
		this.saveFile(image1.getContent(), nombreArchivo+tipo  );
		
		new ThumbNail().createThumbnail(SAVE_PATH + nombreArchivo + tipo,
				PathContext.getImgThumbs()+nombreArchivo + tipo , 100, 100);
		prod.setImage1(nombreArchivo  + tipo);
		
		
	}
	if(image2.getContent()!=null && image2.isVisible()){
		nombreArchivo = new RandomAlphaNum().gen(5)+prod.getCode();	
		Media media = image2.getContent();
		String tipo = media.getName().substring(media.getName().indexOf("."));
		
		this.saveFile(image2.getContent(), nombreArchivo+tipo  );
		
		new ThumbNail().createThumbnail(SAVE_PATH + nombreArchivo + tipo,
				PathContext.getImgThumbs()+nombreArchivo + tipo , 100, 100);
		prod.setImage2( nombreArchivo  + tipo );
	}
	if(image3.getContent()!=null && image3.isVisible()){
		nombreArchivo = new RandomAlphaNum().gen(5)+prod.getCode();	
		Media media = image3.getContent();
		String tipo = media.getName().substring(media.getName().indexOf("."));
		
		this.saveFile(image3.getContent(), nombreArchivo+tipo  );
		
		new ThumbNail().createThumbnail(SAVE_PATH + nombreArchivo + tipo,
				PathContext.getImgThumbs()+nombreArchivo + tipo , 100, 100);
		prod.setImage3( nombreArchivo  + tipo );
	}
	
	generalServ.insertar(prod);	
	
	}catch(Exception e){
		e.printStackTrace();
		Messagebox.show("Error al guardar, favor de verificar");
	}
}
		



private void saveFile(Media media, String nameFile) {
	BufferedInputStream in = null;
	BufferedOutputStream out = null;
	try {
		
		InputStream fin = media.getStreamData();			
		in = new BufferedInputStream(fin);
		File baseDir = new File(SAVE_PATH);
		
		if (!baseDir.exists()) {
			baseDir.mkdirs();
		}

		File file = new File(SAVE_PATH + nameFile);
		
		OutputStream fout = new FileOutputStream(file);
		out = new BufferedOutputStream(fout);
		byte buffer[] = new byte[1024];
		int ch = in.read(buffer);
		while (ch != -1) {
			out.write(buffer, 0, ch);
			ch = in.read(buffer);
		}			
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		try {
			if (out != null) 
				out.close();	
			
			if (in != null)
				in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

class ComboRender implements ComboitemRenderer {
	public void render(Comboitem item, Object value) throws Exception {
		String valor;
		String texto;
		Catproductos m =(Catproductos)value;
		texto = m.getNombre();
		valor = m.getId()+"";
		item.setLabel(texto);
		item.setValue(new Integer(m.getId()));
	}
}


class RandomAlphaNum {
	  public String gen(int length) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = length; i > 0; i -= 12) {
	      int n = min(12, abs(i));
	      sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
	    }
	    return sb.toString();
	  }
	}

	}


	

