package com.station.system.action;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import com.opensymphony.xwork2.ActionSupport;

public class JFreeChartAction extends ActionSupport {

	private static final long serialVersionUID =5752180822913527064L;

    //供ChartResult调用->ActionInvocation.getStack().findValue("chart")

private JFreeChart chart;
    
    @Override
    public String execute() throws Exception {
        //设置数据
//        DefaultPieDataset data =new DefaultPieDataset();
//        data.setValue("Java", new Double(43.2));
//        data.setValue("Visual Basic", new Double(1.0));
//        data.setValue("C/C++", new Double(17.5));
//        data.setValue("C#", new Double(60.0));
//        //生成JFreeChart对象
//        chart = ChartFactory.createPieChart("Pie Chart", data, true,true, true);
    	
//    	// create a dataset...
//        Number[][] data = new Integer[][]
//            {{new Integer(-3), new Integer(-2)},
//             {new Integer(-1), new Integer(1)},
//             {new Integer(2), new Integer(3)}};
//
//        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("S",
//                "C", data);
//
//        // create the chart...
//        chart=ChartFactory.createBarChart(
//            "Bar Chart",
//            "Domain", "Range",
//            dataset,
//            PlotOrientation.HORIZONTAL,
//            true,     // include legend
//            true,
//            true
//        );
    	
    	 Number[][] data = new Integer[][]
			     {{new Integer(-3), new Integer(-2),new Integer(3)},
			      {new Integer(-1), new Integer(1),new Integer(-3)},
			      {new Integer(2), new Integer(3),new Integer(-2)}};
		 CategoryDataset dataset = DatasetUtilities.createCategoryDataset("电缆",
		 "季度", data);
		chart=ChartFactory.createLineChart("温度趋势图", null, "温度范围",
		 dataset);
		

		//自定义字体，解决乱码问题
		Font axisFont=new Font("隶书",Font.PLAIN,12);
		Font titleFont=new Font("宋体",Font.BOLD,18);
		Font legendFont=new Font("黑体",Font.PLAIN,12);
		
		chart.getTitle().setFont(titleFont);
		chart.getLegend().setItemFont(legendFont);
		chart.getLegend().setVisible(true);
		
		chart.getCategoryPlot().getDomainAxis().setTickLabelFont(axisFont);
		chart.getCategoryPlot().getRangeAxis().setTickLabelFont(axisFont);
		chart.getCategoryPlot().getDomainAxis().setLabelFont(axisFont);
		chart.getCategoryPlot().getRangeAxis().setLabelFont(axisFont);
		
        return SUCCESS;
    }

		public JFreeChart getChart() {
		        return chart;
		    }
		
		    public
		void setChart(JFreeChart chart) {
		        this.chart = chart;
		    }
}
