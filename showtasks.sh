#!/usr/bin/env bash

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if ./runcrud.sh; then
   firefox http://localhost:8080/crud/v1/task/getTasks
   end
else
   fail
fi
