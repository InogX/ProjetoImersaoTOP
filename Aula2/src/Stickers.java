import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.TextLayout;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.BasicStroke;

import javax.imageio.ImageIO;

public class Stickers {
    
    public void cria (InputStream inputStream, String nomeArquivo, String texto) throws IOException {

        // Ler a imagem

        // InputStream inputStream = new FileInputStream(File ("entrada/filme.jpg"));
        // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        
        BufferedImage imagemLink = ImageIO.read(inputStream);
        Image imagemOriginal = imagemLink.getScaledInstance(600, 900, Image.SCALE_DEFAULT);


        // Criar uma nova imagem em memória, tendo transparência e novo tamanho
        int largura = 600;
        int altura = 900;
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);


        // Copiar a imagem original pra nova imagem em memória
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);


        // Configurar a escrita
        
        var fonte = new Font(Font.SANS_SERIF,Font.CENTER_BASELINE, 120);
        
        
        TextLayout layoutFont = new TextLayout(texto, fonte, graphics.getFontRenderContext());
        Shape shape = layoutFont.getOutline(null);
        
        graphics.setColor(Color.BLACK);
        graphics.setFont(fonte);
        FontMetrics tamanhoFonte = graphics.getFontMetrics(fonte);
        
        // String texto = "Nunca foi Sorte #ALURA";
        int yFonte = novaAltura - (tamanhoFonte.getAscent()/2);
        int xFonte = (largura - tamanhoFonte.stringWidth(texto))/2;
        

        // Escrever uma frase na nova imagem
                          
        graphics.translate(xFonte, yFonte);
        graphics.setStroke(new BasicStroke(5));
        graphics.draw(shape);

        graphics.setColor(Color.YELLOW);
        graphics.fill(shape);
        

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/"+ nomeArquivo));


    }

}
