package test

import (
	"strings"
	"testing"
)

func TestFormatTopic(t *testing.T) {
	topic := "/a/b/c/"

	if strings.HasPrefix(topic, "/") {
		topic = topic[1:]
	}
	if strings.HasSuffix(topic, "/") {
		topic = topic[:len(topic)-1]
	}
	split := strings.Split(topic, "/")
	var useTopic string
	for _, v := range split {
		useTopic += "."
		useTopic += v
	}
	t.Log(useTopic)
}
