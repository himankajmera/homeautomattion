package com.example.homeautomation;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG = "Home Automation";
    private static final boolean D = true;
    private BluetoothAdapter mBluetoothAdapter = null;
    // Intent request codes
    private static final int REQUEST_CONNECT = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
 // Member object for the chat services
    private Sending mSending = null;
 // Name of the connected device
    private String mConnectedDeviceName = null;
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;
    // String buffer for outgoing messages
    private StringBuffer mOutStringBuffer;
 // Layout Views
    private ListView mConversationView;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;
    private Button mSendButton;
    private Button mSendButton2;
    private Button mSendButton3;
    private Button mSendButton4;
    private Button mrButton;
    private Button mrButton2;
    private Button mrButton3;
    private Button mrButton4;
   
 // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		if(D) Log.e(TAG, "ON CREATE");
		
		 //Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
     // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }
	
	
	 @Override
	    public void onStart() {
	        super.onStart();
	        mText1=(TextView) findViewById(R.id.text1);
	        mText2=(TextView) findViewById(R.id.text2);
	        mText3=(TextView) findViewById(R.id.text3);
	        mText4=(TextView) findViewById(R.id.text4);
	        if(D) Log.e(TAG, "++ ON START ++");

	        // If BT is not on, request that it be enabled.
	        // setupChat() will then be called during onActivityResult
	        if (!mBluetoothAdapter.isEnabled()) {
	            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
	        // Otherwise, setup the chat session
	        } else {
	            if (mSending == null) setupChat();
	        }
	    }
	 
	 
	 @Override
	    public synchronized void onResume() {
	        super.onResume();
	        if(D) Log.e(TAG, "+ ON RESUME +");

	        // Performing this check in onResume() covers the case in which BT was
	        // not enabled during onStart(), so we were paused to enable it...
	        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
	        if (mSending != null) {
	            // Only if the state is STATE_NONE, do we know that we haven't started already
	            if (mSending.getState() == Sending.STATE_NONE) {
	              // Start the Bluetooth chat services
	              mSending.start();
	            }
	        }
	    }
	 
	 
	 
	 private void setupChat() {
	        Log.d(TAG, "setupChat()");

	        // Initialize the array adapter for the conversation thread
	        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.sendmessage);
	        mConversationView = (ListView) findViewById(R.id.in);
	        mConversationView.setAdapter(mConversationArrayAdapter);

	        // Initialize the compose field with a listener for the return key
	        
	        
	        

	        // Initialize the send button with a listener that for click events
	        mSendButton = (Button) findViewById(R.id.button_1);
	        mSendButton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "a";
	                sendMessage(message);
	            }
	        });
	        
	        mSendButton2 = (Button) findViewById(R.id.button_2);
	        mSendButton2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "b";
	                sendMessage(message);
	            }
	        });
	        
	        mSendButton3 = (Button) findViewById(R.id.button_3);
	        mSendButton3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "c";
	                sendMessage(message);
	            }
	        });
	        
	        mSendButton4 = (Button) findViewById(R.id.button_4);
	        mSendButton4.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "d";
	                sendMessage(message);
	            }
	        });
	        
	        mrButton = (Button) findViewById(R.id.button_1f);
	        mrButton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "A";
	                sendMessage(message);
	            }
	        });
	        
	        mrButton3 = (Button) findViewById(R.id.button_3f);
	        mrButton3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "C";
	                sendMessage(message);
	            }
	        });
	        
	        
	        mrButton2 = (Button) findViewById(R.id.button_2f);
	        mrButton2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "B";
	                sendMessage(message);
	            }
	        });
	        
	        mrButton4 = (Button) findViewById(R.id.button_4f);
	        mrButton4.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Send a message using content of the edit text widget
	               // TextView view = (TextView) findViewById(R.id.edit_text_out);
	                String message = "D";
	                sendMessage(message);
	            }
	        });


	        // Initialize the BluetoothChatService to perform bluetooth connections
	        mSending = new Sending(this, mHandler);

	        // Initialize the buffer for outgoing messages
	        mOutStringBuffer = new StringBuffer("");
	    }
	 
	 
	 
	 @Override
	    public synchronized void onPause() {
	        super.onPause();
	        if(D) Log.e(TAG, "- ON PAUSE -");
	    }

	    @Override
	    public void onStop() {
	        super.onStop();
	        if(D) Log.e(TAG, "-- ON STOP --");
	    }

	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	        // Stop the Bluetooth chat services
	        if (mSending != null) mSending.stop();
	        if(D) Log.e(TAG, "--- ON DESTROY ---");
	    }
	
	
	
	private void ensureDiscoverable() {
        if(D) Log.d(TAG, "ensure discoverable");
        if (mBluetoothAdapter.getScanMode() !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
	
	
	
	  private void sendMessage(String message) {
	        // Check that we're actually connected before trying anything
	        if (mSending.getState() != Sending.STATE_CONNECTED) {
	            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
	            return;
	        }

	        // Check that there's actually something to send
	        if (message.length() > 0) {
	            // Get the message bytes and tell the BluetoothChatService to write
	            byte[] send = message.getBytes();
	            mSending.write(send);

	            // Reset out string buffer to zero and clear the edit text field
	            mOutStringBuffer.setLength(0);
	           // mOutEditText.setText(mOutStringBuffer);
	        }
	    }
	  
	/* The action listener for the EditText widget, to listen for the return key
	    private TextView.OnEditorActionListener mWriteListener =
	        new TextView.OnEditorActionListener() {
	        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
	            // If the action is a key-up event on the return key, send the message
	            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
	                String message = view.getText().toString();
	                sendMessage(message);
	            }
	            if(D) Log.i(TAG, "END onEditorAction");
	            return true;
	        }
	    };*/
	    
	    
	    
	    
	    private final void setStatus(int resId) {
	        final ActionBar actionBar = getActionBar();
	        actionBar.setSubtitle(resId);
	    }

	    private final void setStatus(CharSequence subTitle) {
	        final ActionBar actionBar = getActionBar();
	        actionBar.setSubtitle(subTitle);
	    }
	    
	    
	    
	 // The Handler that gets information back from the BluetoothChatService
	    private final Handler mHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
	            case MESSAGE_STATE_CHANGE:
	                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
	                switch (msg.arg1) {
	                case Sending.STATE_CONNECTED:
	                    setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
	                    mConversationArrayAdapter.clear();
	                    break;
	                case Sending.STATE_CONNECTING:
	                    setStatus(R.string.title_connecting);
	                    break;
	                case Sending.STATE_LISTEN:
	                case Sending.STATE_NONE:
	                    setStatus(R.string.title_not_connected);
	                    break;
	                }
	                break;
	            case MESSAGE_WRITE:
	                byte[] writeBuf = (byte[]) msg.obj;
	                // construct a string from the buffer
	                String writeMessage = new String(writeBuf);
	   
	               if ( writeMessage.charAt(0)=='a')
	               {
	            	   mText1.setText("Device1 is on"); 
	               }
	               
	               if ( writeMessage.charAt(0)=='b')
	               {
	            	   mText2.setText("Device2 is on"); 
	               }
	               
	               if ( writeMessage.charAt(0)=='c')
	               {
	            	   mText3.setText("Device3 is on"); 
	               }
	               
	               if ( writeMessage.charAt(0)=='d')
	               {
	            	   mText4.setText("Device4 is on"); 
	               }
	               
	               if ( writeMessage.charAt(0)=='A')
	               {
	            	   mText4.setText("Device1 is off"); 
	               }
	               if ( writeMessage.charAt(0)=='B')
	               {
	            	   mText4.setText("Device2 is off"); 
	               }
	               
	               if ( writeMessage.charAt(0)=='C')
	               {
	            	   mText4.setText("Device3 is off"); 
	               }
	               
	               if ( writeMessage.charAt(0)=='D')
	               {
	            	   mText4.setText("Device4 is off"); 
	               }
	                mConversationArrayAdapter.add("Me:  " + writeMessage + " " );
	                break;
	            case MESSAGE_READ:
	                byte[] readBuf = (byte[]) msg.obj;
	                // construct a string from the valid bytes in the buffer
	                String readMessage = new String(readBuf, 0, msg.arg1);
	                
	                if ( readMessage.charAt(0)=='a')
		               {
		            	   mText1.setText("Device1 is on"); 
		               }
		               
		               if ( readMessage.charAt(0)=='b')
		               {
		            	   mText2.setText("Device2 is on"); 
		               }
		               
		               if ( readMessage.charAt(0)=='c')
		               {
		            	   mText3.setText("Device3 is on"); 
		               }
		               
		               if ( readMessage.charAt(0)=='d')
		               {
		            	   mText4.setText("Device4 is on"); 
		               }
	                if ( readMessage.charAt(0)=='A')
	                {
	                	mText1.setText("Device1 is off");
	                }
	                
	                if ( readMessage.charAt(0)=='B')
	                {
	                	mText2.setText("Device2 is off");
	                }
	                
	                if ( readMessage.charAt(0)=='C')
	                {
	                	mText3.setText("Device3 is off");
	                }
	                
	                if ( readMessage.charAt(0)=='D')
	                {
	                	mText4.setText("Device4 is off");
	                }
	                mConversationArrayAdapter.add(mConnectedDeviceName+":  " + readMessage);
	                break;
	            case MESSAGE_DEVICE_NAME:
	                // save the connected device's name
	                mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
	                Toast.makeText(getApplicationContext(), "Connected to "
	                               + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
	                break;
	            case MESSAGE_TOAST:
	                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
	                               Toast.LENGTH_SHORT).show();
	                break;
	            }
	        }
	    };
	
	    
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if(D) Log.d(TAG, "onActivityResult " + resultCode);
	        switch (requestCode) {
	        case REQUEST_CONNECT:
	            // When DeviceListActivity returns with a device to connect
	            if (resultCode == Activity.RESULT_OK) {
	                connectDevice(data, true);
	            }
	            break;
	        case REQUEST_CONNECT_DEVICE_INSECURE:
	            // When DeviceListActivity returns with a device to connect
	            if (resultCode == Activity.RESULT_OK) {
	                connectDevice(data, false);
	            }
	            break;
	        case REQUEST_ENABLE_BT:
	            // When the request to enable Bluetooth returns
	            if (resultCode == Activity.RESULT_OK) {
	                // Bluetooth is now enabled, so set up a chat session
	                setupChat();
	            } else {
	                // User did not enable Bluetooth or an error occurred
	                Log.d(TAG, "BT not enabled");
	                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
	                finish();
	            }
	        }
	    }

	    private void connectDevice(Intent data, boolean secure) {
	        // Get the device MAC address
	        String address = data.getExtras()
	            .getString(DeviceList.EXTRA_DEVICE_ADDRESS);
	        // Get the BluetoothDevice object
	        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
	        // Attempt to connect to the device
	        mSending.connect(device, secure);
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.options, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        Intent serverIntent = null;
	        switch (item.getItemId()) {
	        case R.id.secure_connect_scan:
	            // Launch the DeviceListActivity to see devices and do scan
	            serverIntent = new Intent(this, DeviceList.class);
	            startActivityForResult(serverIntent, REQUEST_CONNECT);
	            return true;
	       case R.id.insecure_connect_scan:
	            // Launch the DeviceListActivity to see devices and do scan
	            serverIntent = new Intent(this, DeviceList.class);
	            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
	            return true;
	        case R.id.discoverable:
	            // Ensure this device is discoverable by others
	            ensureDiscoverable();
	            return true;
	        
	        case R.id.about:
	        	try {
	        	serverIntent=new Intent("com.example.homeautomation.Aboutdeveloper");
	    		startActivity(serverIntent);
	        	return true;}
	        	catch (Exception e){
	        		
	        	}
	        }
	        return false;
	    }
	

}
