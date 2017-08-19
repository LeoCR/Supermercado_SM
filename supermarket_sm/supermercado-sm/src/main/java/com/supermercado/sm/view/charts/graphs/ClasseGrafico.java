package com.supermercado.sm.view.charts.graphs;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ClasseGrafico {

    //Define um atributo do tipo lineModel onde podemos carregar
    //um gráfico representado por linhas.
    private LineChartModel lineModel;    
    
    //Define um modelo que irá armazenar as linhas do gráfico 
    //e a série de dados (pontos x,y).
    LineChartModel model = new LineChartModel();    
    
    //Define uma série de dados (pontos x,y).
    LineChartSeries serie1 = new LineChartSeries();
    
    //Através deste método get, o gráfico será exibido.
    public LineChartModel getLineModel() {
        return lineModel;
    }
    
    //Executa este método após o Bean ser instanciado.
    @PostConstruct
    public void init() {
        criarLinhasModelo();
    }
    
    //Insere os dados dentro das cordenadas do modelo (a série de dados).
    private LineChartModel iniciarModeloLinear() {
        
        //Define um rótulo na legenda para a série de dados 1.
        serie1.setLabel("Linha 1");
        
        //Loop para inserção de dados.
        for (int i = 0; i < 13; i++) {
            int intRandom = (int)(Math.random() * 50);
            serie1.getData().put(i, intRandom);
        }
        
        //Insere a série 1 dentro do modelo.
        model.addSeries(serie1);
        
        //Retorna o modelo com os dados para o atributo lineModel que por sua 
        //vez retorna para a tela através de seu método get.  
        return model;
                
    }
    
    //Método onde se cria as coordenadas do gráfico e carrega o modelo,
    //também configura as cores, eixo x, y, etc.
    public void criarLinhasModelo() {
        
        //Armazena o modelo, permitindo que o método get
        //retorne para a página HTML.
        lineModel = iniciarModeloLinear();
        
        lineModel.setTitle("Exemplo - Grafico");    //Insere um título no gráfico.
        lineModel.setLegendPosition("e");           //Define a posição da legenda.
        
        Axis yAxis = lineModel.getAxis(AxisType.Y); //Retorna o eixo Y.
        yAxis.setMin(0);                            //Eixo Y começa com zero.
        yAxis.setMax(50);                           //Eixo Y termina em 50.
        yAxis.setTickFormat("%d");                  //Formata números para inteiro.
        yAxis.setLabel("Unidades");                 //Coloca um rótulo no eixo Y.
        
        Axis xAxis = lineModel.getAxis(AxisType.X); //Retorna o eixo X.
        xAxis.setMin(0);                            //Eixo X começa com zero.
        xAxis.setMax(12);                           //Eixo X termina em 12.
        xAxis.setTickFormat("%d");                  //Formata números para inteiro.
        xAxis.setLabel("Tempo");                    //Coloca um rótulo no eixo Y.
        
    }                   
    
}
