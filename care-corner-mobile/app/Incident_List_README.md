FYI
========
this sample code is from a fragment. 
if you are using this from an activity then everywhere you see "*getContect()*" probably can be replaced with "*this*"

try it as is first, and if that doesnt work, try that swap. 

**Note** *toString()* is overwritten in the *Incident* class. So:  
		
		System.out.println(anIncident) 
			
will output an incident report, ie:

		Incident id: Incident_2021_04_21_12_15_13
		Recording File Name: Recording_2021_04_21_12_15_13.3gp
		Journey File Name: Journey_2021_04_21_12_15_13
		Start Time: Wed Apr 21 00:15:13 EDT 2021
		Stop Time: null



Setup
=====
1. in your Activity or Fragment declare the variables needed

		//incident_list variables
		private Vector<Incident> incidents_list;
	    private IncidentListService incidentListService;
	    private boolean isBound;

2. Within your activity/Fragment, place this Override function (mine is near the bottom of my class)

	    private ServiceConnection incidentFilerConnection = new ServiceConnection() {
	        @Override
	        public void onServiceConnected(ComponentName name, IBinder service) {
	            IncidentListService.IncidentListBinder binder = (IncidentListService.IncidentListBinder) service;
	            incidentFiler = binder.getService();
				isBound = true;
	        }

	        @Override
	        public void onServiceDisconnected(ComponentName name) {
	            isBound = false;
	        }
	    };

3. Within your Oncreate or onViewCreated (depending on if its an actvity of Fragment) initialize and bind the service 

		//place in onCreated
	    Intent incidentListIntent = new Intent(getContext(), IncidentListService.class);
	    getContext().bindService(incidentListIntent, incidentFilerConnection, Context.BIND_AUTO_CREATE);
	    incidentListService = new IncidentListService();
		incidents_list = new Vector<Incident>;

4. You should now be able to use the service. 

Loading
====
to manipulate the data you load the array from the device to the array we declared "incidents_list"
here is the simple function I added to my fragment:

    private void loadIncidents(){
        incidents_list = incidentListService.loadIncidents(getContext());
    }

you then use regular Vector syntax plus the getters/setters in Incident.class

Saving
====
if you have changed incident_list and you need to save it back to the device, 
the code can be wrapped in a class like this:

    private void saveIncident() {
        incidentListService.saveIncident(getContext(), incidents_list);
    }


