package main.view;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Point;
import main.util.UserAgents;

public class ConfigDialog extends Dialog {
	
	private String userAgent;
	private Integer timeout;
	private Text text;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Button btnApply;
	private Combo combo;
	
	protected String getUserAgent(){
		return userAgent;
	}
	
	private void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}
	
	private Integer getTimeout(){
		return timeout;
	}
	
	private void setTimeout(Integer timeout){
		this.timeout = timeout;
	}
	
	public ConfigDialog(Shell parent){
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}
	
	public ConfigDialog(Shell parent, int style){
		super(parent, style);
	}
	
	public void open(){
		Shell shell = new Shell(getParent(), getStyle());
		shell.setMinimumSize(new Point(900, 500));
		shell.setSize(329, 231);
		shell.pack();
		shell.setLayout(new FormLayout());
		
		lblNewLabel = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 39);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Choose User Agent");
		
		combo = new Combo(shell, SWT.READ_ONLY);
		combo.setItems(new UserAgents().getUserAgentsAll());
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(lblNewLabel, 18);
		fd_combo.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		combo.setLayoutData(fd_combo);
		combo.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				userAgent = combo.getText();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				userAgent = combo.getText();
			}
		});
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(combo, 18);
		fd_lblNewLabel_1.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Timeout (in seconds)");
		
		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(lblNewLabel_1, 20);
		fd_text.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		text.setLayoutData(fd_text);
		
		btnApply = new Button(shell, SWT.NONE);
		FormData fd_btnApply = new FormData();
		fd_btnApply.bottom = new FormAttachment(100, -44);
		fd_btnApply.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		btnApply.setLayoutData(fd_btnApply);
		btnApply.setText("Apply");
		btnApply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
		
		
		shell.open();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()){
			if (!display.readAndDispatch()){
				display.sleep();
			}
		}
	}
}
