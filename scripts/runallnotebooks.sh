#!/bin/bash
for f in ../notebooks/*.ipynb; do
    jupyter nbconvert --execute "$f" --ExecutePreprocessor.timeout=300
done
