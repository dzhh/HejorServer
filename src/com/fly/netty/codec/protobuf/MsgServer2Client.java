// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: msgserver2c.proto

package com.fly.netty.codec.protobuf;

public final class MsgServer2Client {
  private MsgServer2Client() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Msg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *消息类型
     * </pre>
     *
     * <code>.MsgType msgType = 1;</code>
     */
    int getMsgTypeValue();
    /**
     * <pre>
     *消息类型
     * </pre>
     *
     * <code>.MsgType msgType = 1;</code>
     */
    com.fly.netty.codec.protobuf.MessageType.MsgType getMsgType();

    /**
     * <pre>
     *会话ID
     * </pre>
     *
     * <code>string sessionID = 2;</code>
     */
    java.lang.String getSessionID();
    /**
     * <pre>
     *会话ID
     * </pre>
     *
     * <code>string sessionID = 2;</code>
     */
    com.google.protobuf.ByteString
        getSessionIDBytes();

    /**
     * <pre>
     *消息描述
     * </pre>
     *
     * <code>string msgInfo = 3;</code>
     */
    java.lang.String getMsgInfo();
    /**
     * <pre>
     *消息描述
     * </pre>
     *
     * <code>string msgInfo = 3;</code>
     */
    com.google.protobuf.ByteString
        getMsgInfoBytes();

    /**
     * <pre>
     *机舱编号 
     * </pre>
     *
     * <code>int32 c_id = 4;</code>
     */
    int getCId();

    /**
     * <pre>
     *配置文件地址
     * </pre>
     *
     * <code>string confingUrl = 5;</code>
     */
    java.lang.String getConfingUrl();
    /**
     * <pre>
     *配置文件地址
     * </pre>
     *
     * <code>string confingUrl = 5;</code>
     */
    com.google.protobuf.ByteString
        getConfingUrlBytes();
  }
  /**
   * Protobuf type {@code Msg}
   */
  public  static final class Msg extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Msg)
      MsgOrBuilder {
    // Use Msg.newBuilder() to construct.
    private Msg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Msg() {
      msgType_ = 0;
      sessionID_ = "";
      msgInfo_ = "";
      cId_ = 0;
      confingUrl_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Msg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();

              msgType_ = rawValue;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              sessionID_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              msgInfo_ = s;
              break;
            }
            case 32: {

              cId_ = input.readInt32();
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              confingUrl_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.fly.netty.codec.protobuf.MsgServer2Client.internal_static_Msg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.fly.netty.codec.protobuf.MsgServer2Client.internal_static_Msg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.fly.netty.codec.protobuf.MsgServer2Client.Msg.class, com.fly.netty.codec.protobuf.MsgServer2Client.Msg.Builder.class);
    }

    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    /**
     * <pre>
     *消息类型
     * </pre>
     *
     * <code>.MsgType msgType = 1;</code>
     */
    public int getMsgTypeValue() {
      return msgType_;
    }
    /**
     * <pre>
     *消息类型
     * </pre>
     *
     * <code>.MsgType msgType = 1;</code>
     */
    public com.fly.netty.codec.protobuf.MessageType.MsgType getMsgType() {
      com.fly.netty.codec.protobuf.MessageType.MsgType result = com.fly.netty.codec.protobuf.MessageType.MsgType.valueOf(msgType_);
      return result == null ? com.fly.netty.codec.protobuf.MessageType.MsgType.UNRECOGNIZED : result;
    }

    public static final int SESSIONID_FIELD_NUMBER = 2;
    private volatile java.lang.Object sessionID_;
    /**
     * <pre>
     *会话ID
     * </pre>
     *
     * <code>string sessionID = 2;</code>
     */
    public java.lang.String getSessionID() {
      java.lang.Object ref = sessionID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        sessionID_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *会话ID
     * </pre>
     *
     * <code>string sessionID = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSessionIDBytes() {
      java.lang.Object ref = sessionID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sessionID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MSGINFO_FIELD_NUMBER = 3;
    private volatile java.lang.Object msgInfo_;
    /**
     * <pre>
     *消息描述
     * </pre>
     *
     * <code>string msgInfo = 3;</code>
     */
    public java.lang.String getMsgInfo() {
      java.lang.Object ref = msgInfo_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msgInfo_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *消息描述
     * </pre>
     *
     * <code>string msgInfo = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMsgInfoBytes() {
      java.lang.Object ref = msgInfo_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msgInfo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int C_ID_FIELD_NUMBER = 4;
    private int cId_;
    /**
     * <pre>
     *机舱编号 
     * </pre>
     *
     * <code>int32 c_id = 4;</code>
     */
    public int getCId() {
      return cId_;
    }

    public static final int CONFINGURL_FIELD_NUMBER = 5;
    private volatile java.lang.Object confingUrl_;
    /**
     * <pre>
     *配置文件地址
     * </pre>
     *
     * <code>string confingUrl = 5;</code>
     */
    public java.lang.String getConfingUrl() {
      java.lang.Object ref = confingUrl_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        confingUrl_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *配置文件地址
     * </pre>
     *
     * <code>string confingUrl = 5;</code>
     */
    public com.google.protobuf.ByteString
        getConfingUrlBytes() {
      java.lang.Object ref = confingUrl_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        confingUrl_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (msgType_ != com.fly.netty.codec.protobuf.MessageType.MsgType.ZERO.getNumber()) {
        output.writeEnum(1, msgType_);
      }
      if (!getSessionIDBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, sessionID_);
      }
      if (!getMsgInfoBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, msgInfo_);
      }
      if (cId_ != 0) {
        output.writeInt32(4, cId_);
      }
      if (!getConfingUrlBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, confingUrl_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (msgType_ != com.fly.netty.codec.protobuf.MessageType.MsgType.ZERO.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, msgType_);
      }
      if (!getSessionIDBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, sessionID_);
      }
      if (!getMsgInfoBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, msgInfo_);
      }
      if (cId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, cId_);
      }
      if (!getConfingUrlBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, confingUrl_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.fly.netty.codec.protobuf.MsgServer2Client.Msg)) {
        return super.equals(obj);
      }
      com.fly.netty.codec.protobuf.MsgServer2Client.Msg other = (com.fly.netty.codec.protobuf.MsgServer2Client.Msg) obj;

      boolean result = true;
      result = result && msgType_ == other.msgType_;
      result = result && getSessionID()
          .equals(other.getSessionID());
      result = result && getMsgInfo()
          .equals(other.getMsgInfo());
      result = result && (getCId()
          == other.getCId());
      result = result && getConfingUrl()
          .equals(other.getConfingUrl());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + MSGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + msgType_;
      hash = (37 * hash) + SESSIONID_FIELD_NUMBER;
      hash = (53 * hash) + getSessionID().hashCode();
      hash = (37 * hash) + MSGINFO_FIELD_NUMBER;
      hash = (53 * hash) + getMsgInfo().hashCode();
      hash = (37 * hash) + C_ID_FIELD_NUMBER;
      hash = (53 * hash) + getCId();
      hash = (37 * hash) + CONFINGURL_FIELD_NUMBER;
      hash = (53 * hash) + getConfingUrl().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.fly.netty.codec.protobuf.MsgServer2Client.Msg prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Msg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Msg)
        com.fly.netty.codec.protobuf.MsgServer2Client.MsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.fly.netty.codec.protobuf.MsgServer2Client.internal_static_Msg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.fly.netty.codec.protobuf.MsgServer2Client.internal_static_Msg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.fly.netty.codec.protobuf.MsgServer2Client.Msg.class, com.fly.netty.codec.protobuf.MsgServer2Client.Msg.Builder.class);
      }

      // Construct using com.fly.netty.codec.protobuf.MsgServer2Client.Msg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        msgType_ = 0;

        sessionID_ = "";

        msgInfo_ = "";

        cId_ = 0;

        confingUrl_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.fly.netty.codec.protobuf.MsgServer2Client.internal_static_Msg_descriptor;
      }

      public com.fly.netty.codec.protobuf.MsgServer2Client.Msg getDefaultInstanceForType() {
        return com.fly.netty.codec.protobuf.MsgServer2Client.Msg.getDefaultInstance();
      }

      public com.fly.netty.codec.protobuf.MsgServer2Client.Msg build() {
        com.fly.netty.codec.protobuf.MsgServer2Client.Msg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.fly.netty.codec.protobuf.MsgServer2Client.Msg buildPartial() {
        com.fly.netty.codec.protobuf.MsgServer2Client.Msg result = new com.fly.netty.codec.protobuf.MsgServer2Client.Msg(this);
        result.msgType_ = msgType_;
        result.sessionID_ = sessionID_;
        result.msgInfo_ = msgInfo_;
        result.cId_ = cId_;
        result.confingUrl_ = confingUrl_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.fly.netty.codec.protobuf.MsgServer2Client.Msg) {
          return mergeFrom((com.fly.netty.codec.protobuf.MsgServer2Client.Msg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.fly.netty.codec.protobuf.MsgServer2Client.Msg other) {
        if (other == com.fly.netty.codec.protobuf.MsgServer2Client.Msg.getDefaultInstance()) return this;
        if (other.msgType_ != 0) {
          setMsgTypeValue(other.getMsgTypeValue());
        }
        if (!other.getSessionID().isEmpty()) {
          sessionID_ = other.sessionID_;
          onChanged();
        }
        if (!other.getMsgInfo().isEmpty()) {
          msgInfo_ = other.msgInfo_;
          onChanged();
        }
        if (other.getCId() != 0) {
          setCId(other.getCId());
        }
        if (!other.getConfingUrl().isEmpty()) {
          confingUrl_ = other.confingUrl_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.fly.netty.codec.protobuf.MsgServer2Client.Msg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.fly.netty.codec.protobuf.MsgServer2Client.Msg) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int msgType_ = 0;
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>.MsgType msgType = 1;</code>
       */
      public int getMsgTypeValue() {
        return msgType_;
      }
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>.MsgType msgType = 1;</code>
       */
      public Builder setMsgTypeValue(int value) {
        msgType_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>.MsgType msgType = 1;</code>
       */
      public com.fly.netty.codec.protobuf.MessageType.MsgType getMsgType() {
        com.fly.netty.codec.protobuf.MessageType.MsgType result = com.fly.netty.codec.protobuf.MessageType.MsgType.valueOf(msgType_);
        return result == null ? com.fly.netty.codec.protobuf.MessageType.MsgType.UNRECOGNIZED : result;
      }
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>.MsgType msgType = 1;</code>
       */
      public Builder setMsgType(com.fly.netty.codec.protobuf.MessageType.MsgType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        msgType_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>.MsgType msgType = 1;</code>
       */
      public Builder clearMsgType() {
        
        msgType_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object sessionID_ = "";
      /**
       * <pre>
       *会话ID
       * </pre>
       *
       * <code>string sessionID = 2;</code>
       */
      public java.lang.String getSessionID() {
        java.lang.Object ref = sessionID_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          sessionID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *会话ID
       * </pre>
       *
       * <code>string sessionID = 2;</code>
       */
      public com.google.protobuf.ByteString
          getSessionIDBytes() {
        java.lang.Object ref = sessionID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          sessionID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *会话ID
       * </pre>
       *
       * <code>string sessionID = 2;</code>
       */
      public Builder setSessionID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        sessionID_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *会话ID
       * </pre>
       *
       * <code>string sessionID = 2;</code>
       */
      public Builder clearSessionID() {
        
        sessionID_ = getDefaultInstance().getSessionID();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *会话ID
       * </pre>
       *
       * <code>string sessionID = 2;</code>
       */
      public Builder setSessionIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        sessionID_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object msgInfo_ = "";
      /**
       * <pre>
       *消息描述
       * </pre>
       *
       * <code>string msgInfo = 3;</code>
       */
      public java.lang.String getMsgInfo() {
        java.lang.Object ref = msgInfo_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          msgInfo_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *消息描述
       * </pre>
       *
       * <code>string msgInfo = 3;</code>
       */
      public com.google.protobuf.ByteString
          getMsgInfoBytes() {
        java.lang.Object ref = msgInfo_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          msgInfo_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *消息描述
       * </pre>
       *
       * <code>string msgInfo = 3;</code>
       */
      public Builder setMsgInfo(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        msgInfo_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息描述
       * </pre>
       *
       * <code>string msgInfo = 3;</code>
       */
      public Builder clearMsgInfo() {
        
        msgInfo_ = getDefaultInstance().getMsgInfo();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息描述
       * </pre>
       *
       * <code>string msgInfo = 3;</code>
       */
      public Builder setMsgInfoBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        msgInfo_ = value;
        onChanged();
        return this;
      }

      private int cId_ ;
      /**
       * <pre>
       *机舱编号 
       * </pre>
       *
       * <code>int32 c_id = 4;</code>
       */
      public int getCId() {
        return cId_;
      }
      /**
       * <pre>
       *机舱编号 
       * </pre>
       *
       * <code>int32 c_id = 4;</code>
       */
      public Builder setCId(int value) {
        
        cId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *机舱编号 
       * </pre>
       *
       * <code>int32 c_id = 4;</code>
       */
      public Builder clearCId() {
        
        cId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object confingUrl_ = "";
      /**
       * <pre>
       *配置文件地址
       * </pre>
       *
       * <code>string confingUrl = 5;</code>
       */
      public java.lang.String getConfingUrl() {
        java.lang.Object ref = confingUrl_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          confingUrl_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *配置文件地址
       * </pre>
       *
       * <code>string confingUrl = 5;</code>
       */
      public com.google.protobuf.ByteString
          getConfingUrlBytes() {
        java.lang.Object ref = confingUrl_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          confingUrl_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *配置文件地址
       * </pre>
       *
       * <code>string confingUrl = 5;</code>
       */
      public Builder setConfingUrl(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        confingUrl_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *配置文件地址
       * </pre>
       *
       * <code>string confingUrl = 5;</code>
       */
      public Builder clearConfingUrl() {
        
        confingUrl_ = getDefaultInstance().getConfingUrl();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *配置文件地址
       * </pre>
       *
       * <code>string confingUrl = 5;</code>
       */
      public Builder setConfingUrlBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        confingUrl_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:Msg)
    }

    // @@protoc_insertion_point(class_scope:Msg)
    private static final com.fly.netty.codec.protobuf.MsgServer2Client.Msg DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.fly.netty.codec.protobuf.MsgServer2Client.Msg();
    }

    public static com.fly.netty.codec.protobuf.MsgServer2Client.Msg getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Msg>
        PARSER = new com.google.protobuf.AbstractParser<Msg>() {
      public Msg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Msg(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Msg> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Msg> getParserForType() {
      return PARSER;
    }

    public com.fly.netty.codec.protobuf.MsgServer2Client.Msg getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Msg_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Msg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021msgserver2c.proto\032\021MessageType.proto\"f" +
      "\n\003Msg\022\031\n\007msgType\030\001 \001(\0162\010.MsgType\022\021\n\tsess" +
      "ionID\030\002 \001(\t\022\017\n\007msgInfo\030\003 \001(\t\022\014\n\004c_id\030\004 \001" +
      "(\005\022\022\n\nconfingUrl\030\005 \001(\tB0\n\034com.fly.netty." +
      "codec.protobufB\020MsgServer2Clientb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.fly.netty.codec.protobuf.MessageType.getDescriptor(),
        }, assigner);
    internal_static_Msg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Msg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Msg_descriptor,
        new java.lang.String[] { "MsgType", "SessionID", "MsgInfo", "CId", "ConfingUrl", });
    com.fly.netty.codec.protobuf.MessageType.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
