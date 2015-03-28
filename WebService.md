# Introduction #

A subset of svm4j's functionality is exposed as a RESTful web service. Training sets can be submitted, models obtained and points classified, using appropriate HTTP actions.

The REST functionality is implemented using the [Restlet](http://www.restlet.org/) framework and hosted on Google App Engine at the following root url:

> http://tommerw.appspot.com/svm/

# Details #

The svm4j web service exposes a REST interface allowing for basic training and classification operations.  Resources are represented as XML and exchanged with the server through HTTP GET and POST requests.  Following are instructions and examples for performing the various operations.

## Posting a Training Set ##

You can invoke the SVM engine by posting a training set to the service.  This results in the creation of a model which best classifies the training points.

To post the training set submit a POST request to the following URI:

> http://tommerw.appspot.com/svm/trainingset.

For example we can create a simple training set like so:

```
<trainingset>
  <point>
    <x>0.0, 0.0</x>
    <y>-1.0</y>
  </point>
  <point>
    <x>0.0, 1.0</x>
    <y>1.0</y>
  </point>
</trainingset>
```

And save it as **training-set.xml**.  This is a ridiculously simple training set consisting of two points along the _y_ axis.  The separating hyperplane is simply a horizontal line located at _y_ = 0.5.

> _Note: The only valid y values are -1 and 1.  Since this is a binary classification, no other values are necessary._

We can post the training request with the following cUrl command:

> `curl -X POST --data @training-set.xml --header "Content-Type:text/xml" http://tommerw.appspot.com/trainingset`

The http response will look something like this:

```
<result>
  <status>PASSED</status>
  <modelId>10002</modelId>
</result>
```

The response contains a status and an id for the generated model.  This model has been stored in the App Engine datastore and can be retrieved with the supplied id.

## Retrieving an Existing Model ##

To retrieve a generated model, send a GET request to

> http://tommerw.appspot.com/svm/model/{modelId}

where _modelId_ is the model id.  For example, using the above model, we can enter

> http://tommerw.appspot.com/svm/model/10002

in our browser and the following data will be displayed:

```
<model>
  <id>10002</id>
  <w>0.0, 2.0000057220227063</w>
  <b>-1.0</b>
</model>
```

The model contains two parameters -- the perpendicular vector _w_ and the offset _b_, which define the SVM separating hyperplane.  The points on the hyperplane, _x_, are given by the equation:

> `x . w + b = 0`.

## Classifying a New Point ##

Once you have posted a training set and generated a model, you can classify additional points by posting a classification request.  To do so submit a POST request to

> http://tommerw.appspot.com/svm/model/{modelId}/classification.

This will return the classification for the submitted point, according the model _modelId_.

For example we can create the following XML, representing a point in input space:

```
<point>
  <x>-1.0, -1.0</x>
</point>
```

and save it to **point.xml**.  Then we can post the classification request with the following cUrl command:

> `curl -X POST --data @point.xml --header "Content-Type:text/xml" http://tommerw.appspot.com/model/10002/classification`.

The http response will look something like this:

```
<point>
  <x>-1.0, -1.0</x>
  <y>-1.0</y>
</point>
```

Note that the body of the response is identical to the submitted request except for the additional _y_ element. This is the classification of the point.

## Some Caveats ##

This is a VERY basic web service and some obvious features, like error handling and security, are missing. Still, it should be enough to give you a feel for the functionality of svm4j.