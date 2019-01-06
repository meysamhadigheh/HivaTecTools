package hivatec.ir.hivatectoolstest.model;

import android.graphics.Color;
import android.webkit.WebView;

import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

public class ItemLoading implements ItemBinder {
	@Override
	public int getResourceId() {
		return R.layout.loading_item;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {


		String page = "<svg style=\" background-color:#00000000;" +
				" display: block;\n" +
				"  margin-left: auto;\n" +
				"  margin-right: auto;\" width=\"78px\"  height=\"78px\"  xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 100 100\" preserveAspectRatio=\"xMidYMid\" class=\"lds-globe\">\n" +
				"    <g transform=\"rotate(-23.5 50 50)\">\n" +
				"      <circle cx=\"50\" cy=\"50\" ng-attr-r=\"{{config.radius}}\" ng-attr-fill=\"{{config.fill}}\" ng-attr-stroke=\"{{config.stroke}}\" ng-attr-stroke-width=\"{{config.width}}\" r=\"35\" fill=\"#ffffcb\" stroke=\"#ff7c81\" stroke-width=\"3\"></circle>\n" +
				"      <ellipse cx=\"50\" cy=\"50\" ry=\"0.1\" ng-attr-rx=\"{{config.radius}}\" fill=\"none\" ng-attr-stroke=\"{{config.stroke}}\" ng-attr-stroke-width=\"{{config.width}}\" rx=\"35\" stroke=\"#ff7c81\" stroke-width=\"3\"></ellipse>\n" +
				"      <ellipse cx=\"50\" cy=\"50\" ng-attr-ry=\"{{config.radius}}\" fill=\"none\" ng-attr-stroke=\"{{config.stroke}}\" ng-attr-stroke-width=\"{{config.width}}\" ry=\"35\" stroke=\"#ff7c81\" stroke-width=\"3\" rx=\"15.1667\">\n" +
				"        <animate attributeName=\"rx\" calcMode=\"linear\" values=\"35;0;35\" keyTimes=\"0;0.5;1\" dur=\"1\" begin=\"0s\" repeatCount=\"indefinite\"></animate>\n" +
				"      </ellipse>\n" +
				"      <ellipse cx=\"50\" cy=\"50\" ng-attr-ry=\"{{config.radius}}\" fill=\"none\" ng-attr-stroke=\"{{config.stroke}}\" ng-attr-stroke-width=\"{{config.width}}\" ry=\"35\" stroke=\"#ff7c81\" stroke-width=\"3\" rx=\"8.14333\">\n" +
				"        <animate attributeName=\"rx\" calcMode=\"linear\" values=\"35;0;35\" keyTimes=\"0;0.5;1\" dur=\"1\" begin=\"-0.333s\" repeatCount=\"indefinite\"></animate>\n" +
				"      </ellipse>\n" +
				"      <ellipse cx=\"50\" cy=\"50\" ng-attr-ry=\"{{config.radius}}\" fill=\"none\" ng-attr-stroke=\"{{config.stroke}}\" ng-attr-stroke-width=\"{{config.width}}\" ry=\"35\" stroke=\"#ff7c81\" stroke-width=\"3\" rx=\"31.5023\">\n" +
				"        <animate attributeName=\"rx\" calcMode=\"linear\" values=\"35;0;35\" keyTimes=\"0;0.5;1\" dur=\"1\" begin=\"-0.6667s\" repeatCount=\"indefinite\"></animate>\n" +
				"      </ellipse>\n" +
				"    </g>\n" +
				"  </svg>";

		binder.<WebView>find(R.id.webview).setBackgroundColor(Color.TRANSPARENT);
		binder.<WebView>find(R.id.webview).setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
		binder.<WebView>find(R.id.webview).loadData(page, null, null);
	}
}
