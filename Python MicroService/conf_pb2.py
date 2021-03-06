# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: conf.proto

from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='conf.proto',
  package='com.resumematch.grpc',
  syntax='proto3',
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\nconf.proto\x12\x14\x63om.resumematch.grpc\";\n\rResumeRequest\x12\x11\n\tuniquekey\x18\x01 \x01(\t\x12\x0b\n\x03url\x18\x02 \x01(\t\x12\n\n\x02jd\x18\x03 \x01(\t\"!\n\x0eResumeResponse\x12\x0f\n\x07message\x18\x01 \x01(\t2h\n\rgetMatchRatio\x12W\n\x08getratio\x12#.com.resumematch.grpc.ResumeRequest\x1a$.com.resumematch.grpc.ResumeResponse\"\x00\x62\x06proto3'
)




_RESUMEREQUEST = _descriptor.Descriptor(
  name='ResumeRequest',
  full_name='com.resumematch.grpc.ResumeRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='uniquekey', full_name='com.resumematch.grpc.ResumeRequest.uniquekey', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='url', full_name='com.resumematch.grpc.ResumeRequest.url', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='jd', full_name='com.resumematch.grpc.ResumeRequest.jd', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=36,
  serialized_end=95,
)


_RESUMERESPONSE = _descriptor.Descriptor(
  name='ResumeResponse',
  full_name='com.resumematch.grpc.ResumeResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='message', full_name='com.resumematch.grpc.ResumeResponse.message', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=97,
  serialized_end=130,
)

DESCRIPTOR.message_types_by_name['ResumeRequest'] = _RESUMEREQUEST
DESCRIPTOR.message_types_by_name['ResumeResponse'] = _RESUMERESPONSE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

ResumeRequest = _reflection.GeneratedProtocolMessageType('ResumeRequest', (_message.Message,), {
  'DESCRIPTOR' : _RESUMEREQUEST,
  '__module__' : 'conf_pb2'
  # @@protoc_insertion_point(class_scope:com.resumematch.grpc.ResumeRequest)
  })
_sym_db.RegisterMessage(ResumeRequest)

ResumeResponse = _reflection.GeneratedProtocolMessageType('ResumeResponse', (_message.Message,), {
  'DESCRIPTOR' : _RESUMERESPONSE,
  '__module__' : 'conf_pb2'
  # @@protoc_insertion_point(class_scope:com.resumematch.grpc.ResumeResponse)
  })
_sym_db.RegisterMessage(ResumeResponse)



_GETMATCHRATIO = _descriptor.ServiceDescriptor(
  name='getMatchRatio',
  full_name='com.resumematch.grpc.getMatchRatio',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_start=132,
  serialized_end=236,
  methods=[
  _descriptor.MethodDescriptor(
    name='getratio',
    full_name='com.resumematch.grpc.getMatchRatio.getratio',
    index=0,
    containing_service=None,
    input_type=_RESUMEREQUEST,
    output_type=_RESUMERESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
])
_sym_db.RegisterServiceDescriptor(_GETMATCHRATIO)

DESCRIPTOR.services_by_name['getMatchRatio'] = _GETMATCHRATIO

# @@protoc_insertion_point(module_scope)
