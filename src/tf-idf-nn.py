#!/usr/bin/env python
# coding: utf-8

# In[3]:


from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.preprocessing import LabelEncoder
import matplotlib.pyplot as plt
from tensorflow import keras
import pandas as pd
import json
import numpy as np
import os
try:
    os.chdir(os.path.join(os.getcwd(), 'deeplearning/05-kaggle_cooking'))
    print(os.getcwd())
except IOError:
    pass


# In[4]:


# Dataset Preparation
print ("Read Dataset ... ")
def read_dataset(path):
	return json.load(open(path)) 
train = read_dataset('./data/train.json')
test = read_dataset('./data/test.json')


# In[5]:


print ("Prepare text data of Train and Test ... ")
def generate_text(data):
	text_data = [" ".join(doc['ingredients']).lower() for doc in data]
	return text_data

train_text = generate_text(train)
test_text = generate_text(test)
target = [doc['cuisine'] for doc in train]
print(target)
print(len(target))
target_set=set(target)
print(target_set)
print(len(target_set))


# In[6]:


tfidf = TfidfVectorizer(binary=True)
def tfidf_features(txt, flag):
    if flag == "train":
        x = tfidf.fit_transform(txt)
    else:
        x = tfidf.transform(txt)
    x = x.astype('float16')
    return x 
X = tfidf_features(train_text, flag="train")
X_test = tfidf_features(test_text, flag="test")


# In[7]:


lb = LabelEncoder()
y = lb.fit_transform(target)
print(y)
y = keras.utils.to_categorical(y)
print(y)


# In[8]:


model = keras.Sequential()
model.add(keras.layers.Dense(300, kernel_initializer=keras.initializers.he_normal(seed=1), activation='relu', input_dim=3010))
model.add(keras.layers.Dropout(0.81))
model.add(keras.layers.Dense(300, kernel_initializer=keras.initializers.he_normal(seed=2), activation='relu'))
model.add(keras.layers.Dropout(0.81))
model.add(keras.layers.Dense(20, kernel_initializer=keras.initializers.RandomNormal(mean=0.0, stddev=0.05, seed=4), activation='softmax'))
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])


# In[9]:


history = model.fit(X, y, epochs=30, batch_size=512, validation_split=0.1)
model.save_weights("model.h5")
print("Saved model to disk")


# In[10]:


print(history.history.keys())
plt.plot(history.history['acc'])
plt.plot(history.history['val_acc'])
plt.title('model accuracy')
plt.ylabel('accuracy')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()
# summarize history for loss
plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('model loss')
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()


# In[11]:


predictions_encoded = model.predict(X_test)
predictions_encoded.shape


# In[12]:


predictions = lb.inverse_transform([np.argmax(pred) for pred in predictions_encoded])
print(predictions)


# In[13]:


test_id = [doc['id'] for doc in test]
sub = pd.DataFrame({'id': test_id, 'cuisine': predictions}, columns=['id', 'cuisine'])
sub.head()

